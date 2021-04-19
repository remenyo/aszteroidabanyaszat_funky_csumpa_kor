package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        Log.ctor();
        this.nev = nev;
    }

    /**
     * Ellen�rzi, nem-e fogyott el az �sszes nyersanyag. (F�gv�nyen bel�l cs�kken a sz�ml�l�juk).
     */
    abstract public void ellenorizVesztett();

    /**
     * Ha felsz��re ker�l egy nyersanyag akkor h��v�dik meg ez a f�ggv�ny.
     * 
     * @param a Az aszteroida, ahol a nyersanyag van.
     */
    public void felszinreKerul(Aszteroida a) {
        
    }

    /**
     * Nyersanyag robban�s kezd�pont
     */
    public void Robbanas() {
        Log.call();
        ellenorizVesztett();
    };


    /**
     * Ellen�riz k�t nyersanyagot, hogy azonos t��pus�ak-e
     * 
     * @param ny a m�sik nyersanyag
     * @return boolean {@code true} ha azonosak (n�v alapj�n). {@code false} ha nem.
     */
    public boolean azonos(Nyersanyag ny) { // TODO ez egy equals f�ggv�ny...
        Log.call();
        return this.nev.equals(ny.nev);
    }

    public String getNev() {
        return nev;
    }
}
