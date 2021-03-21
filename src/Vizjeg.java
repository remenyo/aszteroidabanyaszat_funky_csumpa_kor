package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("Vízjég");
        Log.ctor();
        osszesVizjeg++;
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.torolNyersanyag();
            ellenorizVesztett();
        }
    }

    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVizjeg--;
        if (!Szkeleton.Kerdes("Van elég vízjég még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }

    }

}
