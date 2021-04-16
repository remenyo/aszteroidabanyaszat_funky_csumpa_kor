package src;

public class Vas extends Nyersanyag {
    private static Integer osszesVas = 0;

    // L�trej�ttekor n�veli az �sszes vas sz�m�t eggyel
    Vas() {
        super("Vas");
        Log.ctor();
        osszesVas++;
    }

    // Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider�ti van-e m�g
    // el�g, ha nincs vesztett
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
