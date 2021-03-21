package src;

import java.util.Scanner;
import java.util.ArrayList;

public class NyersanyagKoltseg {
    ArrayList<Nyersanyag> szuksegesek;

    public void hozzaadNyersanyag(Nyersanyag ny) {
        szuksegesek.add(ny);
    }

    public boolean Szukseges(Nyersanyag ny) {

    }

    public boolean koltsegSzamitas(ArrayList<Nyersanyag> ny) {
        Scanner in = new Scanner(System.in);
        return Szkeleton().Kerdes("Meg van n�la az �sszes sz�ks�ges nyersanyag? (1:Igen 0:Nem)");
    }

    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny) {
        szuksegesek = ny;
    }

}
