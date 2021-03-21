package src;

import java.util.ArrayList;

public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> koltseg;
    ArrayList<Nyersanyag> masolatTarolo;

    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        koltseg.add(ny);
    }

    // t�lbonyol�tan�, koltsegSzamitasban k�nnyebb
    // public void Szukseges(Nyersanyag ny)
    // {
    // }

    public boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
        Log.call();
        boolean vannyersanyag = Szkeleton.Kerdes("Meg van n�la az �sszes sz�ks�ges nyersanyag?");

        if (vannyersanyag && !Szkeleton.Kerdes("Van el�g nyersanyag a j�t�k megnyer�s�hez?")) {
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

    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
        Log.call();
        koltseg = ny;
    }

}
