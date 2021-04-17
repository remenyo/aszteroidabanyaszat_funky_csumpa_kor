package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;
    protected Boolean lepett = false;

    public Szereplo() {
        Log.call();
        Jatek.leptethetok.add(this); // Szereplõ létrejöttekor a léptethetõk listája is bõvül
    }

    /**
     * Az aszteroida paraméterül kapott sorszámú szomszédjára utazik és a jelenlegirõl törli magát
     * 
     * @param sorszam Az aszteroida azonosí­tója
     */
    public void Mozgas(Integer sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    /**
     * Halálakor hí­vódik meg, törli az aszteroidáról és a léptethetõk közül
     */
    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    /**
     * Amikor napvihar éri a szereplõt, meghal
     */
    public void Napvihar() {
        Log.call();
        Meghal();
    }

    /**
     * Robbanáskor hí­vódik meg, a leszármazottak valósí­tják meg
     */
    public void Robbanas() {
        Log.call();
        Meghal();
        // TODO a leszármazottak valósí­tják meg
    }

    /**
     * Telepes leszármazott visszaadja a szereplõ nyersanyagjait
     * 
     * @return ArrayList<Nyersanyag> a nyersanyagok listája, egyébként {@code null}
     */
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null; // defaultan null-t ad vissza Robot miatt, a leszármazttak felülí­rják
    }

    /**
     * Ezt hí­vja emg a kört levezetõ rendszer
     */
    public void Lepes() {
        Log.call();
    }

    /**
     * Beállí­tja a paraméterül kapott aszteroidát jelenleginek
     * 
     * @param a A beállí­tandó aszteroida
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
