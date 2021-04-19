package src;

import java.util.ArrayList;

/**
 * masolatTarolo - A koltsegSzamitas(ArrayList<Nyersanyag> ny) paraméterként kapott nyersanyagainak
 * másolatát tárolja
 * koltseg - Az építendő eszköz költségét tárolja
 */
public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> koltseg = new ArrayList<Nyersanyag>();
    ArrayList<Nyersanyag> masolatTarolo = new ArrayList<Nyersanyag>();


    /**
     * Hozzáadja a költséghez a paraméterként kapott nyersanyagot
     * 
     * @param ny a költséghez hozzáadandó nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        koltseg.add(ny);

    }

    /**
     * Megkérdezi meg van-e az összes szükséges nyersanyag, majd leellenőrzi, hogy maradt-e még elég
     * a játékban, ha nem vesztett.
     *
     * @param ny nyersanyagok listája
     * @return ha meg van az összes szükséges nyersanyag {@code true} különben {@code false}
     */
    public Boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
        Log.call();
        ArrayList<Nyersanyag> koltsegKlon = new ArrayList<Nyersanyag>();
        koltsegKlon.addAll(koltseg);
        masolatTarolo.clear();
        masolatTarolo.addAll(ny);

        koltsegKlon.removeIf(this::Szukseges);

        if (koltsegKlon.size() == 0) {
            felulirNyersanyagok(ny);
            return true;
        }
        return false;
    }

    /**
     * Ha a masolatTaroloban szerepel a paraméterül kapott nyersanyag, akkor igazzal tér vissza
     * Az elem törlésre kerül a masolatTaroloból
     * @param k - A nyersanyag, ami szerepelhet a masolatTaroloban
     * @return Igazságérték, ha szerepel vagy nem szerepel benne
     */
    private Boolean Szukseges(Nyersanyag k) {
        Log.call();
        for (Nyersanyag m : masolatTarolo)
            if (m.azonos(k)) {
                masolatTarolo.remove(m);
                return true;
            }
        return false;
    }

    /**
     * Felülírja a paraméterül kapott nyersanyagot a nyersanyagTaroloval
     * 
     * @param ny - felülírandó nyersanyagtömb
     */
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
        Log.call();
        ny.clear();
        ny.addAll(masolatTarolo);
    }

}
