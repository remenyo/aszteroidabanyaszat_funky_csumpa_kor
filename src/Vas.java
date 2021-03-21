package src;

public class Vas extends Nyersanyag{
    private static int osszesVas = 0;

    Vas() {
        super("Vas");
        osszesVas++; // ez felt√©telezi hogy az √∫j objektum el van t√°rolva √©s meg van h√≠vva
                  // megsz≈±n√©skor a robban√°
    }

    @Override
    public void ellenorizVesztett() {
    	Log.info("Meghivodott");
    	osszesVas--;
        if (!Szkeleton.Kerdes("Van elÈg vas mÈg a j·tÈkban a gyızelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }
}
