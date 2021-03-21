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
        if (!Szkeleton.Kerdes("Van el�g vas m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }
}
