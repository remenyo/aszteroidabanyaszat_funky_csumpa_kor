package src;

public class Szen extends Nyersanyag {
    private static int osszesSzen = 0;

    Szen() {
        super("Szén");
        osszesSzen++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás

    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        // do nothing;
    }

    @Override
    public void ellenorizNyersanyag() {
        if (osszesSzen < MIN_SZEN) {
            jatekVegeVesztett();
        }
    }

    @Override
    public void robbanas() {
        osszesSzen--;
        super.robbanas();
    }

}
