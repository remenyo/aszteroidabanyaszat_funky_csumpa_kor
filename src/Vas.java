package src;

public class Vas extends Nyersanyag{
    private static int amount = 0;

    Vas() {
        super("Vas");
        amount++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
                  // megszűnéskor a robbanás

    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        // do nothing;
    }

    @Override
    public void robbanas() {
        amount--;
        super.robbanas();
    }
}
