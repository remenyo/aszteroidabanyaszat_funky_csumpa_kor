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
            ellenorizNyersanyag();
        }
    }

    @Override
    public void ellenorizNyersanyag() {
        if (osszesVizjeg < MIN_SZEN) {
            jatekVegeVesztett();
        }
    }

    @Override
    public void robbanas() {
        osszesVizjeg--;
        super.robbanas();
    }
}
