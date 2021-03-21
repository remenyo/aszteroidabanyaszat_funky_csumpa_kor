package src;

import java.util.Scanner;
import java.util.ArrayList;

public class Szereplo implements Leptetheto{
	Aszteroida aszteroida;
    
	public Szereplo() 
	{
		
	}
	
    public void Mozgas() 
    {
        Scanner in = new Scanner(System.in);


        System.out.println("Kérem a szomszéd sorszámát");
        int sorszam = in.nextInt();
        Aszteroida uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
        uj.ellenorizNyert();

    }

    public void Furas() 
    {
        aszteroida.Furas();
    }

    public void Meghal() 
    {
        aszteroida.torolSzereplo(this);
        Jatek.getInstance().torolLeptetheto(this);
    }

    public void Napvihar() 
    {
        this.Meghal();
    }

    public void Robbanas() 
    {
        
    }
    
    public ArrayList<Nyersanyag> getNyersanyagok()
    {
        return null;
    }
    
    public void Lepes(){}
    
    public void beallitAszteroida (Aszteroida a)
    {
        aszteroida = a;
    }
}
