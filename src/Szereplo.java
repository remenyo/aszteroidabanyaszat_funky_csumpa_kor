package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida; // Szereplő aszteroidája amin tartózkodik
    protected Boolean lepett = false;// Megmutatja, hogy lépette a szereplő ebben a körben
    protected JatekView jatekView;
    
    /**
     * Szereplo konstruktora
     */
    public Szereplo() {
        Log.call();
        Jatek.leptethetok.add(this); // Szereplő létrejöttekor a léptethetők listája is bővül
    }

    /**
     * Az aszteroida paraméterül kapott sorszámú szomszédjára utazik és a jelenlegiről törli magát
     * 
     * @param sorszam Az aszteroida azonosítója
     */
    public void Mozgas(Integer sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    /**
     * Halálakor hívódik meg, törli az aszteroidáról és a léptethetők közül
     */
    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    /**
     * Amikor napvihar éri a szereplőt, meghal
     */
    public void Napvihar() {
        Log.call();
        Meghal();
    }

    /**
     * Robbanáskor hívódik meg, a leszármazottak valósítják meg
     */
    public void Robbanas() {
        Log.call();
        Meghal();
    }

    /**
     * Telepes leszármazott visszaadja a szereplő nyersanyagjait
     * 
     * @return nyersanyagok listája, egyébként null
     */
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null;// defaultan null-t ad vissza Robot miatt, a leszármazttak felülírják
    }

    /**
     * Ezt hívja meg a kört levezető rendszer
     */
    public void Lepes() {
        Log.call();
    }

    /**
     * Beállítja a paraméterül kapott aszteroidát jelenleginek
     * 
     * @param a A beállítandó aszteroida
     */
    public void beallitAszteroida(Aszteroida a) {
        Log.call();
        aszteroida = a;
        a.hozzaadSzereplo(this);
    }

    /**
     * Visszaadja az aszteroidajat
     * 
     * @return az aszteroida
     */
    public Aszteroida getAszteroida() {
        return aszteroida;
    }

    /**
     * Visszaadja az, hogy lépette ebben a körben.
     * 
     * @return lépette
     */
    @Override
    public Boolean lepette() {
        return lepett;
    }

    /**
     * Reseteli a lépette attributumot false ra állítja.
     */
    @Override
    public void resetLepett() {
        lepett = false;
    }
    
    public JatekView getView() {
    	return jatekView;
    }
}
