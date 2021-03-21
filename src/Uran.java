package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    Uran() {
        super("Ur�n");
        osszesUran++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (Szkeleton.Kerdes("Napk�zelben van?")) {
            a.Robbanas();
        }
    }

    @Override
    public void ellenorizVesztett() {
    	osszesUran--;
        if (!Szkeleton.Kerdes("Van el�g ur�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.getInstance().jatekVegeVesztett();
        }
    }

    
}
