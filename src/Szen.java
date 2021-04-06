package src;

public class Szen extends Nyersanyag {
    private static Integer osszesSzen = 0;

    Szen() {
        super("Szen");
        Log.ctor();
        osszesSzen++;
    }

    // Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider�ti van-e m�g
    // el�g, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesSzen--;
        if (!Cin.getBool("Van el�g sz�n m�g a j�t�kban a gy�zelemhez?")) {
            Jatek.jatekVegeVesztett();
        }
    }

}
