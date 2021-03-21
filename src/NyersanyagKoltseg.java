package src;

import java.util.Scanner;
import java.util.ArrayList;

public class NyersanyagKoltseg {
ArrayList<Nyersanyag> szuksegesek; 
    
    public void hozzaadNyersanyag(Nyersanyag ny)
     {
         szuksegesek.add(ny);
     }
    
    public boolean Szukseges(Nyersanyag ny)
    {
        
    }
    
    public boolean koltsegSzamitas(ArrayList<Nyersanyag> ny)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Meg van nála az összes szükséges nyersanyag?\n 1:Igen\n 2:Nem");
        int megvan = in.nextInt();
        return megvan==1;
    }
    
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny)
    {
        szuksegesek = ny;
    }
     
}
