package src;

import java.util.Scanner;
import java.util.ArrayList;

public class NyersanyagKoltseg {
ArrayList<Nyersanyag> koltseg; 
ArrayList<Nyersanyag> masolatTarolo; 

    
    public void hozzaadNyersanyag(Nyersanyag ny)
     {
         koltseg.add(ny);
     }
    
    //public void Szukseges(Nyersanyag ny)
    //{
        
    //}
    
    //public boolean koltsegSzamitas(ArrayList<Nyersanyag> ny)
    //{
    //    Scanner in = new Scanner(System.in);
    //    return new Szkeleton().Kerdes("Meg van nála az összes szükséges nyersanyag? (1:Igen 0:Nem)");
    //}
    
    public void koltsegSzamitas(ArrayList<Nyersanyag> ny) {
    	ArrayList<Nyersanyag> koltsegKlon = koltseg;
    	masolatTarolo = ny;
    	
    	for(Nyersanyag m : masolatTarolo) {
    		for(Nyersanyag k : koltsegKlon) {
    			if(m.azonos(k)) {
    				
    			}
    		}
    	}
    	
    	
    }
    
    public void felulirNyersanyagok(ArrayList<Nyersanyag> ny)
    {
        koltseg = ny;
    }
     
}
