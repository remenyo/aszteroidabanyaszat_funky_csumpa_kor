package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    public Szereplo() {
        Log.call();
        Jatek.leptethetok.add(this); // Szereplõ létrejöttekor a léptethetõk listája is bõvül
    }


    /**
     * Az aszteroida paraméterül kapott sorszámú szomszédjára utazik és a jelenlegirõl törli magát
     * 
     * @param sorszam Az aszteroida azonosítója
     */
    public void Mozgas(int sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    /**
     * Fúrja az aszteroidát, amin áll
     */
    public void Furas() {
        Log.call();
        aszteroida.Furas();
    }

    /**
     * Halálakor hívódik meg, törli az aszteroidáról és a léptethetõk közül
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
     * Robbanáskor hívódik meg, a leszármazottak valósítják meg
     */
    public void Robbanas() {
        Log.call();
        // TODO a leszármazottak valósítják meg
    }


    /**
     * Telepes leszármazott visszaadja a szereplõ nyersanyagjait
     * 
     * @return ArrayList<Nyersanyag> a nyersanyagok listája, egyébként {@code null}
     */
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null; // defaultan null-t ad vissza Robot miatt, a leszármazttak felülírják
    }

    /**
     * Ezt hívja emg a kört levezetõ rendszer
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
}
