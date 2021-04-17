package src;

public class Uran extends Nyersanyag {
    private static Integer osszesUran = 0;
    private Integer napfenyErte;

    // Létrejöttekor növeli az összes urán számot eggyel
    Uran() {
        super("Urán");
        Log.ctor();
        osszesUran++;
        napfenyErte = 0;
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
            napfenyErte++;
            if (napfenyErte == 3) {
                a.Robbanas();
            }
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
        if (osszesUran < 3) {
            Jatek.jatekVegeVesztett();
        }
    }

    public String toString() {
        return osszesUran.toString() + ":" + napfenyErte.toString() /* + (char) 13 + (char) 10 */;
    }

    public Integer getnapFenyerte() {
        return napfenyErte;
    }

}
