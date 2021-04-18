package src;

import java.util.ArrayList;

/**
 * masolatTarolo - A koltsegSzamitas(ArrayList<Nyersanyag> ny) paramĂ©terĂĽl kapott nyersanyagainak
 * mĂˇsolatĂˇt tĂˇrolja. koltseg - Az Ă©pĂ­tendĹ‘ eszkĂ¶z kĂ¶ltsĂ©gĂ©t tĂˇrolja.
 */
public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> koltseg = new ArrayList<Nyersanyag>();
    ArrayList<Nyersanyag> masolatTarolo = new ArrayList<Nyersanyag>();


    /**
     * Hozzďż˝adja a kďż˝ltsďż˝ghez a paramďż˝terďż˝l kapott nyersanyagot
     * 
     * @param ny a kďż˝ltsďż˝ghez hozzďż˝adandďż˝ nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        koltseg.add(ny);

    }

    /**
     * Megkďż˝rdezi meg van-e az ďż˝sszes szďż˝ksďż˝ges nyersanyag, majd leellenďż˝rzi, hogy
     * maradt-e mďż˝g elďż˝g a jďż˝tďż˝kban, ha nem vesztett.
     *
     * @param ny nyersanyagok listďż˝ja
     * @return ha meg van az ďż˝sszes szďż˝ksďż˝ges nyersanyag {@code true} kďż˝lďż˝nben
     *         {@code false}
     */
    // Majd a kďż˝sz ďż˝sszehasonlďż˝tďż˝s
    public Boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
        Log.call();
        ArrayList<Nyersanyag> koltsegKlon = koltseg;
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
     * Ha a masolatTaroloban szerepel a paramĂ©terĂĽl kapott nyersanyag, akkor igazzal tĂ©r vissza
     * Az elem tĂ¶rlĂ©sre kerĂĽl a masolatTarolobĂłl
     * 
     * @param k - A nyersanyag, ami szerepelhet a masolatTaroloban
     * @return IgazsĂˇgĂ©rtĂ©k, ha szerepel vagy nem szerepel benne
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
     * Felďż˝lďż˝rja a paramĂ©terĂĽl kapott nyersanyagot a nyersanyagTaroloval
     * 
     * @param ny - felĂĽlĂ­randĂł nyersanyagtĂ¶mb
     */
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
        Log.call();
        ny = masolatTarolo;
    }

}
