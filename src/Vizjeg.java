package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    //Létrejöttekor növeli az összes vízjég számát eggyel
    Vizjeg() {
        super("Vízjég");
        Log.ctor();
        osszesVizjeg++;
    }

    //Amikor felszínre kerül kideríti, hogy napközelben van-e, ha igen törli magát az aszteroidáról és megnézi maradt-e még elég
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.torolNyersanyag();
            ellenorizVesztett();
        }
    }

    //Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még elég, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVizjeg--;
        if (!Szkeleton.Kerdes("Van elég vízjég még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }

    }

}
