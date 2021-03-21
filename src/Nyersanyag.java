package src;

import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Nyersanyag {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        this.nev = nev;
    }

    public static void ellenorizNyersanyag() {
        logger.log(Level.FINE, "Ellenoriz");
        // akkor itt kérdezni kéne
    };

    abstract public void felszinreKerul(Aszteroida a);

    public void robbanas() {
        ellenorizNyersanyag();
    };

    public boolean azonos(Nyersanyag ny) {
        return this.nev.equals(ny.nev);
    }
}
