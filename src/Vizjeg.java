package src;

public class Vizjeg extends Nyersanyag {
    private static Integer osszesVizjeg = 0;

    Vizjeg() {
        this(true);
    }

    Vizjeg(Boolean jatekonBelul) {
        super("Vízjég");
        Log.ctor();
        if (jatekonBelul)
            osszesVizjeg++;
        jatekView = new VizjegView();
    }

    protected static void reset() {
        osszesVizjeg = 0;
    }

    /**
     * Amikor felszínre kerül kideríti, hogy napközelben van-e, ha igen törli magát az
     * aszteroidáról és megnézi maradt-e még elég
     * 
     * @param a az aszteroida ahol a nyersanyag van
     */
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.torolNyersanyag();
            ellenorizVesztett();
        }
    }

    /**
     * Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még
     * elég, ha nincs vesztett
     */
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVizjeg--;
        if (osszesVizjeg < 3) {
            Jatek.jatekVegeVesztett();
        }

    }
    /**
     * Vízjég toStringje
     * 
     * @return Összes vízjég száma stringként.
     */
    public String toString() {
        return osszesVizjeg.toString();
    }

}
