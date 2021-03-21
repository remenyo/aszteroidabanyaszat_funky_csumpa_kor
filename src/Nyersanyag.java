package src;

import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        this.nev = nev;
    }

    abstract public void ellenorizVesztett();

    abstract public void felszinreKerul(Aszteroida a);

    public void Robbanas() {
        ellenorizNyersanyag();
    };

    public boolean azonos(Nyersanyag ny) {
        return this.nev.equals(ny.nev);
    }
}
