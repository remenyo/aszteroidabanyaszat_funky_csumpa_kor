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

    // Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e
    // még
    // elég, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVas--;
        if (osszesVas < 3) {
            Jatek.jatekVegeVesztett();
        }
    }
    /**
     * Vas toStringje
     * 
     * @return Összes vas száma stringként.
     */
    public String toString() {
        return osszesVas.toString();
    }
}
