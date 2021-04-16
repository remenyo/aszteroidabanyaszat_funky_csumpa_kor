package src;

public class Vas extends Nyersanyag {
    private static Integer osszesVas = 0;

    // Létrejöttekor növeli az összes vas számát eggyel
    Vas() {
        super("Vas");
        Log.ctor();
        osszesVas++;
    }

    // Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még
    // elég, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesVas--;
        if (osszesVas<3) {
            Jatek.jatekVegeVesztett();
        }
    }
    
    public String toString() {
    	return osszesVas.toString()+ (char) 13 + (char) 10;
    }
}
