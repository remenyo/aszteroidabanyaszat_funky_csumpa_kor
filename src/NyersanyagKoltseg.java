package src;

import java.util.ArrayList;

/**
 * masolatTarolo - A koltsegSzamitas(ArrayList<Nyersanyag> ny) paraméterül
 * kapott nyersanyagainak másolatát tárolja.
 * koltseg - Az építendő eszköz költségét tárolja.
 */
public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> koltseg = new ArrayList<Nyersanyag>();
    ArrayList<Nyersanyag> masolatTarolo = new ArrayList<Nyersanyag>();


    /**
     * Hozz�adja a k�lts�ghez a param�ter�l kapott nyersanyagot
     * 
     * @param ny a k�lts�ghez hozz�adand� nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        koltseg.add(ny);
    }

    /**
     * Megk�rdezi meg van-e az �sszes sz�ks�ges nyersanyag, majd leellen�rzi, hogy maradt-e m�g el�g
     * a j�t�kban, ha nem vesztett.
     *
     * @param ny nyersanyagok list�ja
     * @return ha meg van az �sszes sz�ks�ges nyersanyag {@code true} k�l�nben {@code false}
     */
     //Majd a k�sz �sszehasonl�t�s
     public Boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
    	 Log.call();
         ArrayList<Nyersanyag> koltsegKlon = koltseg;
         masolatTarolo = ny;

         koltsegKlon.removeIf(this::Szukseges);

         if(koltsegKlon.size()==0){
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
     private Boolean Szukseges(Nyersanyag k){
    	 Log.call();
         for(Nyersanyag m : masolatTarolo)
             if(m.azonos(k)) {
                 masolatTarolo.remove(m);
                 return true;
             }
         return false;
     }

    /**
     * Fel�l�rja a paraméterül kapott nyersanyagot a nyersanyagTaroloval
     * 
     * @param ny - felülírandó nyersanyagtömb
     */
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
    	Log.call();
        ny = masolatTarolo;
    }

}
