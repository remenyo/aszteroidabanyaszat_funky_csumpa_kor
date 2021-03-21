package src;

import java.util.ArrayList;
import java.util.Scanner;

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
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }

    public Hely getSzomszed(int i) {
    	Log.info("Meghivodott");
        return szomszedok.get(i);
    }

    public void Robbanas() {
    	Log.info("Meghivodott");
        nyersanyag.Robbanas();//nem e null
        if(szomszedok.size()!=0) {
        	szomszedok.get(0).szomszedRobbant(this); 
        	if(szomszedok.size()==2) {
        		szomszedok.get(1).szomszedRobbant(this);
        	}
        }
        if(szereplok.size()!=0) {
        	szereplok.get(0).Robbanas(); 
        }
        nap.torolAszteroida(this);

    }

    public void Napvihar() {
    	Log.info("Meghivodott");
        boolean valasz = Szkeleton.Kerdes("Ures az aszteroida es ki van furva?");
        if (!valasz) {
            szereplok.get(0).Napvihar();
        }

    }

    public void Furas() {
    	Log.info("Meghivodott");
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a köpeny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }

    public void torolSzomszed(Hely h) {
    	Log.info("Meghivodott");
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomszéd?")) {
            szereplok.get(0).Meghal();
            Robbanas();
        }
    }

    public void hozzaadSzomszed(Hely h) {
    	Log.info("Meghivodott");
        szomszedok.add(h);
    }

    public Nyersanyag Banyaszat() {
    	Log.info("Meghivodott");
        Nyersanyag visszaAdando = nyersanyag;
        nyersanyag = null;
        return nyersanyag;
    }

    public void ellenorizNyert(){
    	Log.info("Meghivodott");
    	if(Szkeleton.Kerdes("Megvan az összes nyersanyag?")) {
    		Jatek.getInstance().jatekVegeNyert();
    	}
    }

    public void hozzaadSzereplo(Szereplo sz) {
    	Log.info("Meghivodott");
        szereplok.add(sz);
    }

    public void torolSzereplo(Szereplo sz) {
    	Log.info("Meghivodott");
        szereplok.remove(sz);
    }

    public void torolNyersanyag() {
    	Log.info("Meghivodott");
        nyersanyag = null;
    }

    public void hozzaadNyersanyag(Nyersanyag ny) {
    	Log.info("Meghivodott");
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }

    public void Utazas(Szereplo sz) {
    	Log.info("Meghivodott");
        sz.beallitAszteroida(this);
    }

    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
    	Log.info("Meghivodott");
        urbazisKoltseg = k;
    }

    public void szomszedRobbant(Aszteroida a) {
    	Log.info("Meghivodott");
        torolSzomszed(a);
    }

    public boolean isNapkozelben() {
    	Log.info("Meghivodott");
        return Szkeleton.Kerdes("Napközelben van az aszteroida?");
    }

    public void setNyersanyag(Nyersanyag ny) {
    	Log.info("Meghivodott");
        nyersanyag = ny;
    }

	public ArrayList<Hely> getSzomszedok() {
		Log.info("Meghivodott");
		return szomszedok;
	}
    
}
