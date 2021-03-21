package src;

public class Vas extends Nyersanyag{
    private static int osszesVas = 0;

    Vas() {
        super("Vas");
        osszesVas++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
                  // megszűnéskor a robbaná
    }

    @Override
    public void ellenorizVesztett() {
    	Log.info("Meghivodott");
    	osszesVas--;
        if (!Szkeleton.Kerdes("Van el�g vas m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }
}
