package src;

public class Szen extends Nyersanyag {
    private static int osszesSzen = 0;

    Szen() {
        super("Szen");
        Log.ctor();
        osszesSzen++;
    }
    
    //
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesSzen--;
        if (!Szkeleton.Kerdes("Van el�g sz�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
