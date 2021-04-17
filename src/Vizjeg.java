package src;

public class Vizjeg extends Nyersanyag {
    private static Integer osszesVizjeg = 0;

    // Létrejöttekor növeli az összes ví­zjég számát eggyel
    Vizjeg() {
        super("Ví­zjég");
        Log.ctor();
        osszesVizjeg++;
    }


    /**
     * Amikor felszí­nre kerül kiderí­ti, hogy napközelben van-e, ha igen törli magát az
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
     * Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kiderí­ti van-e még
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

    public String toString() {
        return osszesVizjeg.toString() /* + (char) 13 + (char) 10 */;
    }

}
