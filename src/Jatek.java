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
	        System.out.println("Gratul�lunk nyert�l!! :)");
	    }
	    
	    public void jatekVegeVesztett()
	    {
	        System.out.println("Gratul�lunk vesztett�l !! :)");
	    }
	    
	    public void jatekInditas()
	    {
	        
	    }
}
