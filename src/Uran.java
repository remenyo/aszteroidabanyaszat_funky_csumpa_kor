package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    Uran() {
        super("Ur�n");
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
        if (!Szkeleton.Kerdes("Van el�g ur�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
