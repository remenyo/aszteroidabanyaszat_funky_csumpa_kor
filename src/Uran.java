package src;

public class Uran extends Nyersanyag {
    private static int osszesUran = 0;

    // L�trej�ttekor n�veli az �sszes ur�n sz�mot eggyel
    Uran() {
        super("Ur�n");
        Log.ctor();
        osszesUran++;
    }

    // Felsz�nre ker�ltekor kider�ti, hogy napk�zelben van-e, ha igen robban
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.Robbanas();
        }
    }

    //Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider�ti van-e m�g el�g, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesUran--;
        if (!Szkeleton.Kerdes("Van el�g ur�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
