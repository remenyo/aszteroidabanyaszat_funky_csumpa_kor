package src;

public class Szen extends Nyersanyag {
    private static Integer osszesSzen = 0;

    Szen() {
        this(true);
    }

    Szen(Boolean jatekonBelul) {
        super("Szen");
        Log.ctor();
        if (jatekonBelul)
            osszesSzen++;
    }
    /**
     * Reseteli az osszes szén számot.
     * 
     */
    protected static void reset() {
        osszesSzen = 0;
    }

    // Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e
    // még
    // elég, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesSzen--;
        if (osszesSzen < 3) {
            Jatek.jatekVegeVesztett();
        }
    }

    /**
     * Szén toStringje
     * 
     * @return Összes szén száma stringként.
     */
    public String toString() {
        return osszesSzen.toString();
    }

}
