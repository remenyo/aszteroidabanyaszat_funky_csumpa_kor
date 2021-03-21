package src;

public class Vizjeg extends Nyersanyag {
    private static int amount = 0;

    Vizjeg() {
        super("Vízjég");
        amount++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (a.isNapközelben()) {
            a.torolNyersanyag(this);
            super.ellenorizNyersanyag();
        }
    }

    @Override
    public void robbanas() {
        amount--;
        super.robbanas();
    }
}
