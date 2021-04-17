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
    }

    protected static void reset() {
        osszesVizjeg = 0;
    }

    /**
     * Amikor felsz��nre ker�l kider��ti, hogy napk�zelben van-e, ha igen t�rli mag�t az
     * aszteroid�r�l �s megn�zi maradt-e m�g el�g
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
     * Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider��ti van-e m�g
     * el�g, ha nincs vesztett
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
