package src;

public class Vas extends Nyersanyag {
    private static Integer osszesVas = 0;

    Vas() {
        this(true);
    }

    Vas(Boolean jatekonBelul) {
        super("Vas");
        Log.ctor();
        if (jatekonBelul)
            osszesVas++;
    }

    protected static void reset() {
        osszesVas = 0;
    }

    // Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider��ti van-e
    // m�g
    // el�g, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVas--;
        if (osszesVas < 3) {
            Jatek.jatekVegeVesztett();
        }
    }

    public String toString() {
        return osszesVas.toString();
    }
}
