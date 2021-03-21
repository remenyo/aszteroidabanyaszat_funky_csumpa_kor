package src;

public class Uran extends Nyersanyag {

    Uran() {
        super("Urán");
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (a.isNapközelben()) {
            a.Robbanas();
        }
    }
}
