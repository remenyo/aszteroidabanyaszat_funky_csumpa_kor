package src;

public class Vizjeg extends Nyersanyag {

    Vizjeg() {
        super("Vízjég");
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
        if (a.isNapközelben()) {
            a.torolNyersanyag(this);
            super.ellenorizNyersanyag();
        }
    }
}
