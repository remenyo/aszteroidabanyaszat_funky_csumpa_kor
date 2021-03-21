package src;

public class Szen extends Nyersanyag {
    private static int osszesSzen = 0;

    Szen() {
        super("Szen");
        osszesSzen++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás

    }

    @Override
    public void ellenorizVesztett() {
    	Log.info("Meghivodott");
    	osszesSzen--;
        if (!Szkeleton.Kerdes("Van el�g sz�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }

}
