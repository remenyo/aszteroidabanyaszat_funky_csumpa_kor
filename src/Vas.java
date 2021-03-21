package src;

public class Vas extends Nyersanyag {
    private static int osszesVas = 0;

    Vas() {
        super("Vas");
        Log.ctor();
        osszesVas++;
    }

    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVas--;
        if (!Szkeleton.Kerdes("Van elég vas még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }
}
