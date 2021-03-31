package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        Log.ctor();
        this.nev = nev;
    }

    /**
     * Ellenõrzi, nem-e fogyott el az összes nyersanyag. (Fügvényen belül csökken a számlálójuk).
     */
    abstract public void ellenorizVesztett();


    /**
     * Ha felszíre kerül egy nyersanyag akkor hívódik meg ez a függvény.
     * 
     * @param a Az aszteroida, ahol a nyersanyag van.
     */
    public void felszinreKerul(Aszteroida a) {

    }

    /**
     * Nyersanyag robbanás kezdõpont
     */
    public void Robbanas() {
        Log.call();
        ellenorizVesztett();
    };


    /**
     * Ellenõriz két nyersanyagot, hogy azonos típusúak-e
     * 
     * @param ny a másik nyersanyag
     * @return boolean {@code true} ha azonosak (név alapján). {@code false} ha nem.
     */
    public boolean azonos(Nyersanyag ny) { // TODO ez egy equals függvény...
        Log.call();
        return this.nev.equals(ny.nev);
    }
}
