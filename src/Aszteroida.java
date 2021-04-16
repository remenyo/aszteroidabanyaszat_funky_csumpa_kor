package src;

import java.util.ArrayList;

/**
 *
 * @author Danesz
 */
public class Aszteroida extends Hely {
    private Integer reteg;
    private Boolean napkozel;
    private Boolean elorejelzesvan;
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
    Aszteroida(Nap nap) {
    	this.nap = nap;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
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
        if(nyersanyag != null) {
        	nyersanyag.Robbanas();
        }
        for(Hely h: szomszedok) {
        	h.szomszedRobbant(this); 
        }
        for(Szereplo sz: szereplok) {
        	sz.Robbanas();
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
       elorejelzesvan = false;
       for(Hely h:szomszedok) {
        	h.szomszedNapvihar();
       }
       if(reteg!= 0 || nyersanyag != null ) {
    	   for(Szereplo sz: szereplok) {
    		   sz.Napvihar();
    	   }
       }
    }

    /**
     * Az aszteroida ezzel a f�ggv�nyel f�rhat�. Ha elfogy a k�peny, felsz�nre ker�l az Aszteroida
     * nyersanyaga.
     */
    public void Furas() {
        Log.call();
        if(reteg>0) {
        	reteg--;
            if (reteg == 0) {
            	nyersanyag.felszinreKerul(this);
            }
        }
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
        if (szomszedok.size()==0) {
            for(Szereplo sz: szereplok) {
            	sz.Meghal();
            }                         
            Robbanas();                // Robban�s el�ttt az�rt kell mindenkin megh�vni a meghalt
                                       // mert a robban�sban a robot nem halna meg
                                       // hanem csak m�sik szomsz�dos aszteroid�ra ker�lne de mivel
                                       // nincs szomsz�d ez�rt � is meghal.
            
        }
    }

    /**
     * Aszteroida hozz�ad�sa a szomsz�dok list�hoz.
     * 
     * @param h A hozz�adadnd� hely.
     */
    public void hozzaadSzomszed(Hely h) { 
        Log.call();
        szomszedok.add(h);
    }
    public void setElorejelzesvan() {
    	elorejelzesvan = true;
    }
    public Boolean getElorejelzesvan() {
    	return elorejelzesvan;
    }
    public void setReteg(Integer ujReteg) {
    	reteg = ujReteg;
    }
    
    //Nap mozg�sa miatt ker�l be az �s f�ggv�ny�nek megval�s�t�sa
    public void utazasHely(Hely hely){
    	   hozzaadSzomszed(hely);
    }
    
    /**
     * B�ny�sz�skor h�v�dik meg a benne l�v� nyersanyagot adja vissza �s �ress� teszi mag�t
     * 
     * @return A kib�ny�szott nyersanyag. Ha nem siker�l a m�velet, {@code null}-al t�r vissza.
     */
    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag; // kimentj�k az �rt�ket
        torolNyersanyag(); // �ress� tessz�k az aszteroid�t
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
        ArrayList<Nyersanyag> nyLista = new ArrayList<Nyersanyag>();
        for(Szereplo sz: szereplok) {
        	nyLista.addAll(sz.getNyersanyagok());
        }  
        if (urbazisKoltseg.koltsegSzamitas(nyLista)) {
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
        return napkozel;
    }
    
    public void setNapkozel(Boolean ujNapkozel) {
    	napkozel = ujNapkozel;
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
    
    public String toString() {
    	String kimenet="";
    	kimenet+=reteg.toString()+":"+napkozel.toString();
    	
    	Szkeleton sz = Szkeleton.INSTANCE;
    	kimenet+=":[";
    	for (Szereplo szereplo : szereplok) {
    		kimenet+=sz.getID(szereplo)+":";
		}
    	kimenet = kimenet.substring(0, kimenet.length()-2);
    	kimenet+="]:[";
    	for (Hely szomszed : szomszedok) {
			kimenet+=sz.getID(szomszed)+":";
		}
    	kimenet = kimenet.substring(0, kimenet.length()-2);
    	kimenet += "]:"+sz.getID(nyersanyag).toString();
    	kimenet += "" + (char) 13 + (char) 10;
    	return kimenet;
    	
    }

}
