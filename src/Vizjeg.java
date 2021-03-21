package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("Vízjég");
        osszesVizjeg++; // ez feltÃ©telezi hogy az Ãºj objektum el van tÃ¡rolva Ã©s meg van hÃ­vva
        // megszÅ±nÃ©skor a robbanÃ¡s
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
    	Log.info("Meghivodott");
        if (a.isNapkozelben()) {
            a.torolNyersanyag();
            ellenorizVesztett();
        }
    }

    @Override
    public void ellenorizVesztett() {
    	Log.info("Meghivodott");
    	osszesVizjeg--;
        if (!Szkeleton.Kerdes("Van elég vízjég még a játékban a gyõzelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
        
    }


}
