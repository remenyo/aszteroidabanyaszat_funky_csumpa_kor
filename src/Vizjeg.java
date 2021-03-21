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
        if (a.isNapkozelben()) {
            a.torolNyersanyag();
            ellenorizVesztett();
        }
    }

    @Override
    public void ellenorizVesztett() {
    	osszesVizjeg--;
        if (!Szkeleton.Kerdes("Van el�g v�zj�g m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
        Log.info("Meghivodott");
    }


}
