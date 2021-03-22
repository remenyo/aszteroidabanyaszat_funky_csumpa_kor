package src;

import java.util.ArrayList;

/**
 *
 * @author Danesz
 */
public class Aszteroida extends Hely {
    private int reteg;
    private boolean napkozel;
    private ArrayList<Szereplo> szereplok;
    private ArrayList<Hely> szomszedok;
    private Nap nap;
    private Nyersanyag nyersanyag;
    private static NyersanyagKoltseg urbazisKoltseg;
    
    //Aszteroida konstruktora v�gs� verzi�ban nem �gy lesz tesztek kedv��rt van jelenleg �gy
    Aszteroida(int reteg, boolean napkozel, Nap nap, Nyersanyag nyersanyag) {
        Log.ctor();
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }
    
    //vissza adja azt az egy szomsz�d Hely-et aki az i. indexen �ll.
    public Hely getSzomszed(int i) {
        Log.call();
        return szomszedok.get(i);
    }
    
    //Az aszteroida felrobban�s mindenkit meg�lve aki rajta �ll �s kivonva mag�t a szomsz�dai k�z�l
    //az ifek a tesztek miatt kellettek ne fussunk indexOutOfBounds hib�ba.
    //robban�s ut�n kiszedj�k a nap elemei k�z�l is ahol az �sszes aszteroida tal�lhat�
    public void Robbanas() {
        Log.call();
        nyersanyag.Robbanas();// nem e null
        if (szomszedok.size() != 0) {
            szomszedok.get(0).szomszedRobbant(this);
            if (szomszedok.size() == 2) {
                szomszedok.get(1).szomszedRobbant(this);
            }
        }
        if (szereplok.size() != 0) {
            szereplok.get(0).Robbanas();
        }
        nap.torolAszteroida(this);

    }
    
    //Napviharkor h�v�dik meg. Ha van benne nyersanyag vagy nincs �tf�rva  a k�penye a rajta l�v� szerepl�k meghalnak
    public void Napvihar() {
        Log.call();
        if (!Szkeleton.Kerdes("Ures az aszteroida es ki van furva?")) {
                szereplok.get(0).Napvihar();  
                szereplok.get(0).Napvihar();  //k�tszer kell a 0�son mert amikor az els� elemen megh�v�dik akkor az kit�rl�dik �s az 1 es veszi �t a hely�t.
        }

    }
    
    //F�r�skor h�v�dik meg. Ha elfogy a k�peny a felsz�nre ker�l az Aszteroida nyersanyaga
    public void Furas() {
        Log.call();
        reteg--;
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a k�peny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }
    
    //Ha egy aszteroida felrobban akkor a szomsz�djain v�gig h�vja ezt hogy t�r�lj�k ki �t a szomsz�djaik k�z�l
    //Ha egy aszteroid�nak nem marad szomsz�dja felrobban 
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomsz�d?")) {
            szereplok.get(0).Meghal(); // V�gs� verzi�ban ez az �sszes az aszteroid�n tart�zkod� szerepl�n megh�v�dik itt csak konkr�t esetre.
            							//Robban�s el�ttt az�rt kell mindenkin megh�vni a meghalt mert a robban�sban a robot nem halna meg 
            							// hanem csak m�sik szomsz�dos aszteroid�ra ker�lne de mivel nincs szomsz�d ez�rt � is meghal.
            Robbanas();
        }
    }
    
    //Ezzel vesz�nk fel egy Helyet az aszteroida szomsz�djaihoz
    public void hozzaadSzomszed(Hely h) {
        Log.call();
        szomszedok.add(h);
    }
    
    //B�ny�sz�skor h�v�dik meg a benne l�v� nyersanyagot adja vissza �s �ress� teszi mag�t
    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag; //kimentj�k az �rt�ket
        nyersanyag = null; //�ress� tessz�k az aszteroid�t
        return visszaAdando; // nem null �rt�ket visszaadjuk.
    }
    
    //Ha az �sszes nyersanyag megtal�lhat� az aszteroid�n ami kell a j�t�k megnyer�s�hez a j�t�k v�get �r.
    public void ellenorizNyert() {
        Log.call();
        if (Szkeleton.Kerdes("Megvan az �sszes nyersanyag?")) {
            Jatek.jatekVegeNyert();
        }
    }
    
    //A rajta l�v� szerepl�kh�z felvesz�nk egyet.
    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }
    
    //A rajta l�v� szerepl�k k�z�l elt�vol�tunk egyet
    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
    }
    
    //Az aszteroid�t �ress� tessz�k. Nem kell neki param�ter hisz egy Aszteroid�ba egy nyersanyag lehet.
    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
    }
    
    //Az Aszteroid�ba belerakunk egy nyersanyagot ami a vissza rak�s felt�telei miatt a felsz�nre ker�l egyb�l
    //Ett�l fel is robbanhat ha Ur�n volt vagy el is p�rologhat ha v�zj�g.
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }
    
    
    //Mozg�skor h�v�dik meg. A szerepl�nek azon tulajdons�g�t �ll�tja �t hogy melyik aszteroid�n �ll.
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
    }
    
    //A nyersanyagkolts�get (BillOfMaterial) �ll�tja be. Jelen esteben hogy mi kell az �rb�zis fel�p�t�s�hez.
    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
        Log.call();
        urbazisKoltseg = k;
    }
    
    //Akkor h�v�dik meg ha egy szomsz�dja felrobbant �s azt ki kell t�r�lni a szomsz�djai k�z�l.
    public void szomszedRobbant(Aszteroida a) {
        Log.call();
        torolSzomszed(a);
    }
    
    //Napk�zelben belis�g lek�rdez�se
    public boolean isNapkozelben() {
        Log.call();
        return Szkeleton.Kerdes("Napk�zelben van az aszteroida?");
    }
    
    //Az Aszteroid�ba beSettel�nk egy nyersanyagot. Ilyenkor nem h�v�dik meg a felsz�re ker�l�s ez a tesztekhez kellett.
    public void setNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
    }
    
    //Vissza adja a szomsz�dok list�j�t.
    public ArrayList<Hely> getSzomszedok() {
        Log.call();
        return szomszedok;
    }

}
