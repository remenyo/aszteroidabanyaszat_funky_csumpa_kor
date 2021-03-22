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
        if (!Szkeleton.Kerdes("Van elég szén még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
