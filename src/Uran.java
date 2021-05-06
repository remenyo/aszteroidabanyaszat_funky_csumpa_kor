package src;

public class Uran extends Nyersanyag {
    private static Integer osszesUran = 0;
    private Integer napfenyErte;

    Uran() {
        this(true);
        jatekView = new UranView();
    }

    Uran(Boolean jatekonBelul) {
        super("Urán");
        Log.ctor();
        if (jatekonBelul)
            osszesUran++;
        napfenyErte = 0;
        jatekView = new UranView();
    }

    protected static void reset() {
        osszesUran = 0;
    }

    /**
     * Felszínre kerültekor kideríti, hogy napközelben van-e, ha igen robban
     * 
     * @param a aszteroida, amiben a nyersanyag van
     */
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            napfenyErte++;
            if (napfenyErte == 3) {
                a.Robbanas();
            }
        }
    }

    /**
     * Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még
     * elég, ha nincs vesztett
     */
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesUran--;
        if (osszesUran < 3) {
            Jatek.jatekVegeVesztett();
        }
    }

    /**
     * Urán toStringje
     * 
     * @return Összes szén száma stringként + napfényérte stringként.
     */
    public String toString() {
        return osszesUran.toString() + ":" + napfenyErte.toString();
    }
    /**
     * Szén toStringje
     * 
     * @return Hányszor érte napfény.
     */
    public Integer getnapFenyerte() {
        return napfenyErte;
    }

}
