package src;

public class Uran extends Nyersanyag {
    private static int amount = 0;

    Uran() {
        super("Urán");
        amount++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (a.isNapközelben()) {
            a.Robbanas();
        }
    }

    @Override
    public void robbanas() {
        amount--;
        super.robbanas();
    }
}