package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("Vízjég");
        Log.ctor();
        osszesVizjeg++; // ez feltÃ©telezi hogy az Ãºj objektum el van tÃ¡rolva Ã©s meg van hÃ­vva
        // megszÅ±nÃ©skor a robbanÃ¡s
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
