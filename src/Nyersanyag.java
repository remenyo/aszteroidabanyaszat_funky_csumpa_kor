package src;

import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Nyersanyag {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        this.nev = nev;
    }

    abstract public void ellenorizNyersanyag();

    abstract public void felszinreKerul(Aszteroida a);

    public void robbanas() {
        ellenorizNyersanyag();
    };

    public boolean azonos(Nyersanyag ny) {
        return this.nev.equals(ny.nev);
    }
}
