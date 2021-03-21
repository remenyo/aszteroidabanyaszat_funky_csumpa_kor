package src;

public class Vizjeg extends Nyersanyag {
    private static int osszesVizjeg = 0;

    Vizjeg() {
        super("Vízjég");
        osszesVizjeg++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (a.isNapközelben()) {
            a.torolNyersanyag(this);
            ellenorizVesztett();
        }
    }

    @Override
    public void ellenorizVesztett() {
        if (osszesVizjeg < MIN_SZEN) {
            jatekVegeVesztett();
        }
        Log.info("Meghivodott");
    }

    @Override
    public void robbanas() {
        osszesVizjeg--;
        super.robbanas();
    }

    public void ellenorizVesztett() {
        boolean valasz = Szkeleton().Kerdes("Van eleg nyersanyag a jatek folytatasahoz?\n1-Igen,2-Nem");
        if (!valasz) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }

}
