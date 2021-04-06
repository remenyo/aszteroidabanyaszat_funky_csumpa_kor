package src;

import java.util.ArrayList;

public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> koltseg;
    ArrayList<Nyersanyag> masolatTarolo;


    /**
     * Hozz�adja a k�lts�ghez a param�ter�l kapott nyersanyagot
     * 
     * @param ny a k�lts�ghez hozz�adand� nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        koltseg.add(ny);
    }


    // t�lbonyol�tan�, koltsegSzamitasban k�nnyebb
    // public void Szukseges(Nyersanyag ny)
    // {
    // }

    /**
     * Megk�rdezi meg van-e az �sszes sz�ks�ges nyersanyag, majd leellen�rzi, hogy maradt-e m�g el�g
     * a j�t�kban, ha nem vesztett.
     * 
     * @param ny nyersanyagok list�ja
     * @return ha meg van az �sszes sz�ks�ges nyersanyag {@code true} k�l�nben {@code false}
     */
    public boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
        Log.call();
        boolean vannyersanyag = Cin.getBool("Meg van n�la az �sszes sz�ks�ges nyersanyag?");

        if (vannyersanyag && !Cin.getBool("Van el�g nyersanyag a j�t�k megnyer�s�hez?")) {
            Jatek.jatekVegeVesztett();
        }
        return vannyersanyag;
    }

    // Majd a k�sz �sszehasonl�t�s
    // public void koltsegSzamitas(ArrayList<Nyersanyag> ny) {
    // ArrayList<Nyersanyag> koltsegKlon = koltseg;
    // masolatTarolo = ny;

    // for(int m=0; m<=masolatTarolo.size(); m++) {
    // for(int k=0; k<=koltsegKlon.size(); k++) {
    // if(masolatTarolo.get(m).azonos(koltsegKlon.get(k))) {
    // masolatTarolo.remove(m);
    // koltsegKlon.remove(k);
    // m++;
    // break;
    // }
    // }
    // }
    // }

    /**
     * Fel�l�rja a k�lt�sget a param�ter�l kapott list�val.
     * 
     * @param ny a nyersanyaglista
     */
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
        Log.call();
        koltseg = ny;
    }

}
