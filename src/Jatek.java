package src;

import java.util.ArrayList;

public class Jatek {
	 int telepesszam;
	    ArrayList<Leptetheto> leptethetok;
	    
	    public void Kor()
	    {
	        for (Leptetheto leptetheto : leptethetok) {
				leptetheto.Lepes();
			}
	    } 
	    
	    public void torolLeptetheto(Leptetheto l)
	    {
	        leptethetok.remove(l);
	    }
	    
	    public void telepesMeghal()
	    {
	        telepesszam--;
	    }
	    
	    public void jatekVegeNyert()
	    {
	        System.out.println("Gratulálunk nyertél!! :)");
	    }
	    
	    public void jatekVegeVesztett()
	    {
	        System.out.println("Gratulálunk vesztettél !! :)");
	    }
	    
	    public void jatekInditas()
	    {
	        
	    }
}
