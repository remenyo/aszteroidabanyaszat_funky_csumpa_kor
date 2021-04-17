package src;

public class Szen extends Nyersanyag {
    private static Integer osszesSzen = 0;

    Szen() {
        super("Szen");
        Log.ctor();
        osszesSzen++;
    }

    // Amikor megsemmisül a nyersanyag csökkenti az összes számát eggyel, majd kideríti van-e még
    // elég, ha nincs vesztett
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesSzen--;
        if (osszesSzen < 3) {
            Jatek.jatekVegeVesztett();
        }
    }

    public String toString() {
        return osszesSzen.toString() /* + (char) 13 + (char) 10 */;
    }

}
