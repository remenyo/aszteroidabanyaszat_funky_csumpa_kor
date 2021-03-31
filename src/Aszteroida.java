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

    /**
     * Aszteroida konstruktor
     * 
     * @param reteg      Az aszteroid�n l�v� k�r�teg kezdeti vastags�ga (0-sok)
     * @param napkozel   Napk�zelben van-e az aszteroida
     * @param nap        A nap referenci�ja
     * @param nyersanyag Az aszteroida magj�ban tal�lhat� nyersanyag (opcoin�lis)
     */
    Aszteroida(int reteg, boolean napkozel, Nap nap, Nyersanyag nyersanyag) {
        Log.ctor();
        // TODO Aszteroida konstruktora v�gs� verzi�ban nem �gy lesz tesztek kedv��rt van jelenleg
        // �gy
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }

    /**
     * Vissza adja azt az egy szomsz�d Hely-et aki az i. indexen �ll.
     * 
     * @param i A szomsz�d azonos�t�ja
     * @return A szomsz�d Hely aki az i. indexen �ll.
     */
    public Hely getSzomszed(int i) {
        Log.call();
        return szomszedok.get(i);
    }

    /**
     * Az aszteroida robban�s minden rajta tart�zkod� entit�st felrobbantja majd kivonva mag�t a
     * szomsz�dai k�z�l, majd a j�t�kb�l.
     */
    public void Robbanas() {
        Log.call();
        // TODO rossz a sorrend, el�bb robbantjuk a robotokat, azt�n t�r�lj�k a szomsz�dokat
        // TODO az ifek csak a tesztek miatt kellettek ne fussunk indexOutOfBounds hib�ba.
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

    /**
     * Napvihar eset�n h�v�dik meg. Ha van benne nyersanyag, vagy nincs �tf�rva az aszteroida
     * k�penye, az entit�sokon is megh�v�dik a Napvihar f�ggv�ny.
     * 
     * @see Nap
     */
    public void Napvihar() {
        Log.call();
        if (!Szkeleton.Kerdes("Ures az aszteroida es ki van furva?")) {
            szereplok.get(0).Napvihar();
            szereplok.get(0).Napvihar(); // k�tszer kell a 0�son mert amikor az els� elemen
                                         // megh�v�dik akkor az kit�rl�dik �s az 1 es veszi �t a
                                         // hely�t.
        }
    }

    /**
     * Az aszteroida ezzel a f�ggv�nyel f�rhat�. Ha elfogy a k�peny, felsz�nre ker�l az Aszteroida
     * nyersanyaga.
     */
    public void Furas() {
        Log.call();
        reteg--;
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a k�peny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }

    /**
     * Egy Aszteroida-t t�r�l a szomsz�dok list�b�l.
     * 
     * Ha egy aszteroida felrobban, akkor a szomsz�djain v�gig h�vja ezt hogy t�r�lj�k ki �t a
     * szomsz�djaik k�z�l Ha egy aszteroid�nak nem marad szomsz�dja felrobban
     * 
     * @param h a t�rlend� aszteroida
     */
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomsz�d?")) {
            szereplok.get(0).Meghal(); // V�gs� verzi�ban ez az �sszes az aszteroid�n tart�zkod�
                                       // szerepl�n megh�v�dik itt csak konkr�t esetre.
                                       // Robban�s el�ttt az�rt kell mindenkin megh�vni a meghalt
                                       // mert a robban�sban a robot nem halna meg
                                       // hanem csak m�sik szomsz�dos aszteroid�ra ker�lne de mivel
                                       // nincs szomsz�d ez�rt � is meghal.
            Robbanas();
        }
    }

    /**
     * Aszteroida hozz�ad�sa a szomsz�dok list�hoz.
     * 
     * @param h A hozz�adadnd� hely.
     */
    public void hozzaadSzomszed(Hely h) { // TODO mi�rt hely? nem csak aszteroid�t t�rolunk a
                                          // szomsz�dokban?
        Log.call();
        szomszedok.add(h);
    }

    /**
     * B�ny�sz�skor h�v�dik meg a benne l�v� nyersanyagot adja vissza �s �ress� teszi mag�t
     * 
     * @return A kib�ny�szott nyersanyag. Ha nem siker�l a m�velet, {@code null}-al t�r vissza.
     */
    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag; // kimentj�k az �rt�ket
        nyersanyag = null; // �ress� tessz�k az aszteroid�t
        return visszaAdando; // nem null �rt�ket visszaadjuk.
    }

    /**
     * A j�t�k "megnyert"-s�g�t ellen�rzi az aszteroid�n.
     * 
     * Ha az �sszes nyersanyag megtal�lhat� az aszteroid�n ami kell a j�t�k megnyer�s�hez, a j�t�k
     * v�get �r.
     */
    public void ellenorizNyert() {
        Log.call();
        if (Szkeleton.Kerdes("Megvan az �sszes nyersanyag?")) {
            Jatek.jatekVegeNyert();
        }
    }

    /**
     * Hozz�ad a szerepl�k list�hoz egy {@code Szerepl�}-t
     * 
     * @param sz Az �j szerepl�
     */
    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }

    /**
     * Elt�vol�t a szerepl�k list�b�l egy {@code Szerepl�}-t
     * 
     * @param sz A t�rlend� szerepl�
     */
    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
    }

    /**
     * Az aszteroida magj�ban tal�lhat� nyersanyagot elfelejti, (ezzel t�r�lve azt.)
     * 
     * Nem kell neki param�ter hisz egy Aszteroid�ba egy nyersanyag lehet.
     */
    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
    }

    /**
     * Az Aszteroida magj�ba belerak egy nyersanyagot.
     * 
     * (A vissza rak�s felt�telei miatt a felsz�nre is ker�l egyb�l. Ett�l fel is robbanhat, ha pl.
     * Ur�n volt, vagy el is p�rologhat, ha v�zj�g.)
     * 
     * @param ny Az aszteroida magj�ba helyezend� nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }

    /**
     * Mozg�skor h�v�dik meg. A szerepl�t �tutaztatja erre az aszteroid�ra.
     * 
     * @param sz Az utaz� szerepl�
     */
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
        // TODO furi ez a f�ggv�ny TBH
    }

    /**
     * A nyersanyagkolts�get {@code BillOfMaterial} �ll�tja be.
     * 
     * Jelen esteben hogy mi kell az �rb�zis fel�p�t�s�hez.
     * 
     * @param k A be�ll�tand� {@code NyersanyagKoltseg}
     */
    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
        Log.call();
        urbazisKoltseg = k;
    }

    /**
     * Akkor h�v�dik meg ha egy szomsz�dja felrobbant �s azt ki kell t�r�lni a szomsz�djai k�z�l.
     */
    public void szomszedRobbant(Aszteroida a) {
        Log.call();
        torolSzomszed(a);
    }

    /**
     * Napk�zelbens�g lek�rdez�se
     * 
     * @return Aszteroida napk�zels�ge
     */
    public boolean isNapkozelben() {
        Log.call();
        return Szkeleton.Kerdes("Napk�zelben van az aszteroida?");
    }

    /**
     * Az Aszteroid�ba beSettel�nk egy nyersanyagot. Ilyenkor nem h�v�dik meg a felsz�re ker�l�s.
     * 
     * @param ny Be�ll�tand� nyersanyag
     */
    public void setNyersanyag(Nyersanyag ny) {
        // TODO ez a tesztekhez kellett. DE KELL IS! lesz teszt m�g
        Log.call();
        nyersanyag = ny;
    }

    /**
     * Az aszteroida szomsz�djainak list�j�t adja vissza.
     * 
     * @return Szomsz�dok list�ja
     */
    public ArrayList<Hely> getSzomszedok() {
        Log.call();
        return szomszedok;
    }

}
