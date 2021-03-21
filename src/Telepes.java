package src;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok = new ArrayList<Nyersanyag>();
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
	private ArrayList<Portal> portal = new ArrayList<Portal>();
	
	public void Mozgas() {
		super.Mozgas(); 
		aszteroida.ellenorizNyert();
	}
	
	public void Banyaszat() {
		Nyersanyag temp = aszteroida.Banyaszat();
		if (temp != null) {
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		}
	}
	
	public ArrayList<Nyersanyag> getNyersanyagok() {
		return nyersanyagok;
	}
	
	public void Lepes() {
		Scanner in = new Scanner(System.in); 
	    String s = in.nextLine(); 
		// nemtom hogy itt mizu
		
	}
	
	public void epitRobot() {
		boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok); //??????????????????????
		if(vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
		}
	}
	
	public void epitPortal() {
		if(portal.isEmpty()) {
			boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok); //??????????????????????
			if(vaneleg) {
				Portal p1 = new Portal();
				Portal p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
			}
		}
	}
	
	public void lehelyezPortal(Portal p) {
		p.beallitVegpont(aszteroida);
	}
	
	public void torolPortal(Portal p) {
		portal.remove(p);
	}
	
	public void visszarakNyersanyag(Nyersanyag ny) {
			aszteroida.hozzaadNyersanyag(ny);
	}
	
	public void Robbanas() {
		Meghal();
	}
	
	public void Meghal() {
		aszteroida.torolSzereplo(this);
		for (Portal p : portal) 
		{ 
		    p.Robbanas();
		}
		for (Nyersanyag ny : nyersanyagok) 
		{ 
		    ny.Robbanas();
		}
		jatek.torolLeptetheto(this);
		jatek.telepesMeghal();
	}
	
	public void hozzaadNyersanyag(Nyersanyag ny) {
		nyersanyag.add(ny);
	}
	
	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		epiteskoltseg.add(k);
	}
}
