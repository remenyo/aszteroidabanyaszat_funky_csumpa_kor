package src;

public class Vas extends Nyersanyag{
    private static int osszesVas = 0;

    Vas() {
        super("Vas");
        osszesVas++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
                  // megszűnéskor a robbanás

    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        // do nothing;
    }

    @Override
    public void ellenorizNyersanyag() {
        if (osszesVas < MIN_VAS) {
            jatekVegeVesztett();
        }
    }

    @Override
    public void robbanas() {
        osszesVas--;
        super.robbanas();
    }
}
