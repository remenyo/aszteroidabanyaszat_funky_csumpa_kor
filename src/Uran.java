package src;

public class Uran extends Nyersanyag {
    private static Integer osszesUran = 0;

    // Létrejöttekor növeli az összes urán számot eggyel
    Uran() {
        super("Urán");
        Log.ctor();
        osszesUran++;
    }


    /**
     * Felszínre kerültekor kideríti, hogy napközelben van-e, ha igen robban
     * 
     * @param a aszteroida, amiben a nyersanyag van
     */
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            a.Robbanas();
        }
    }

    /**
     * Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még
     * elég, ha nincs vesztett
     */
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesUran--;
        if (!Cin.getBool("Van elég urán még a játékban a gyõzelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
