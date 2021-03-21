package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    Uran() {
        super("Urán");
        Log.ctor();
        osszesUran++;
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.Robbanas();
        }
    }

    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesUran--;
        if (!Szkeleton.Kerdes("Van elég urán még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
