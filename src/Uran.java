package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    Uran() {
        super("Ur·n");
        osszesUran++; // ez felt√©telezi hogy az √∫j objektum el van t√°rolva √©s meg van h√≠vva
        // megsz≈±n√©skor a robban√°s
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (Szkeleton.Kerdes("Napkˆzelben van?")) {
            a.Robbanas();
        }
    }

    @Override
    public void ellenorizVesztett() {
    	osszesUran--;
        if (!Szkeleton.Kerdes("Van elÈg ur·n mÈg a j·tÈkban a gyızelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }

    
}
