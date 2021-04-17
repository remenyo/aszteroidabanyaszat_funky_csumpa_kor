package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;
    protected Boolean lepett = false;

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
        // TODO a leszármazottak valósítják meg
    }

    /**
     * Telepes leszármazott visszaadja a szereplő nyersanyagjait
     * 
     * @return ArrayList<Nyersanyag> a nyersanyagok listája, egyébként {@code null}
     */
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null; // defaultan null-t ad vissza Robot miatt, a leszármazttak felülírják
    }

    /**
     * Ezt hívja emg a kört levezető rendszer
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

    public Aszteroida getAszteroida() {
        return aszteroida;
    }
}
