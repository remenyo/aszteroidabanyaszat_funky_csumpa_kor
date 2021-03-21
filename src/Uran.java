package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    Uran() {
        super("Urán");
        osszesUran++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (new Szkeleton().Kerdes("Napk�zelben van (1:Igen 0:Nem)")) {
            a.Robbanas();
        }
    }

    @Override
    public void ellenorizNyersanyag() {
        if (osszesUran < MIN_URAN) {
            jatekVegeVesztett();
        }
    }

    @Override
    public void robbanas() {
        osszesUran--;
        super.robbanas();
    }
}
