package src;

public class Szen extends Nyersanyag {
    private static int osszesSzen = 0;

    Szen() {
        super("Szen");
        osszesSzen++; // ez felt√©telezi hogy az √∫j objektum el van t√°rolva √©s meg van h√≠vva
        // megsz≈±n√©skor a robban√°s

    }

    @Override
    public void ellenorizVesztett() {
    	Log.info("Meghivodott");
    	osszesSzen--;
        if (!Szkeleton.Kerdes("Van elÈg szÈn mÈg a j·tÈkban a gyızelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }

}
