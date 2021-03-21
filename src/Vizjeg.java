package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("V�zj�g");
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
        if (!Szkeleton.Kerdes("Van el�g v�zj�g m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }

    }

}
