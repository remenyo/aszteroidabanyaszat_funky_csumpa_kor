package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("V�zj�g");
        osszesVizjeg++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
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
        if (!Szkeleton.Kerdes("Van el�g v�zj�g m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
        
    }


}
