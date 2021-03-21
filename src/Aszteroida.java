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

    Aszteroida(int reteg, boolean napkozel, Nap nap, Nyersanyag nyersanyag) {
        Log.ctor();
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }

    public Hely getSzomszed(int i) {
        Log.call();
        return szomszedok.get(i);
    }

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

    public void Napvihar() {
        Log.call();
        if (!Szkeleton.Kerdes("Ures az aszteroida es ki van furva?")) {
            for (Szereplo szereplo : szereplok) {
                szereplo.Napvihar();
            }
        }

    }

    public void Furas() {
        Log.call();
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a köpeny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }

    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomszéd?")) {
            for (Szereplo szereplo : szereplok) {
                szereplo.Meghal();
            }
            Robbanas();
        }
    }

    public void hozzaadSzomszed(Hely h) {
        Log.call();
        szomszedok.add(h);
    }

    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag;
        nyersanyag = null;
        return visszaAdando;
    }

    public void ellenorizNyert() {
        Log.call();
        if (Szkeleton.Kerdes("Megvan az összes nyersanyag?")) {
            Jatek.jatekVegeNyert();
        }
    }

    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }

    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
    }

    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
    }

    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }

    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
    }

    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
        Log.call();
        urbazisKoltseg = k;
    }

    public void szomszedRobbant(Aszteroida a) {
        Log.call();
        torolSzomszed(a);
    }

    public boolean isNapkozelben() {
        Log.call();
        return Szkeleton.Kerdes("Napközelben van az aszteroida?");
    }

    public void setNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
    }

    public ArrayList<Hely> getSzomszedok() {
        Log.call();
        return szomszedok;
    }

}
