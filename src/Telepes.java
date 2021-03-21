import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telepes extends Szereplo {
	private List<Nyersanyag> nyersanyagok = new ArrayList<Nyersanyag>();
	private static List<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
	private List<Portal> portal = new ArrayList<Portal>();
	
	public mozgas() {
		super.mozgas(); //????????ezt h kell?
		a.ellenorizNyert();
	}
	
	public void Banyaszat() {
		Nyersanyag temp = a.Banyaszat();
		if (temp != null) {
			nyersanyagok.add(temp);
			a.ellenorizNyert();
		}
	}
	
	public List<Nyersanyag> getNyersanyagok() {
		return nyersanyagok;
	}
	
	public void Lepes() {
		Scanner in = new Scanner(System.in); 
	    String s = in.nextLine(); 
		// nemtom hogy itt mizu
		
	}
	
	public void epitRobot() {
		boolean vaneleg = k.get(1).koltsegSzamitas(nyersanyagok); //??????????????????????
		if(vaneleg) {
			Robot r = new Robot();
			a.Utazas(r);
		}
	}
	
	public void epitPortal() {
		if(portal.isEmpty()) {
			boolean vaneleg = k.get(0).koltsegSzamitas(nyersanyagok); //??????????????????????
			if(vaneleg) {
				Portal p1 = new Portal();
				Portal p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
			}
		}
	}
	
	public void lehelyezPortal(Portal p) {
		p.beallitVegpont(a);
	}
	
	public void torolPortal(Portal p) {
		portal.remove(p);
	}
	
	public void visszarakNyersanyag(Nyersanyag ny) {
			a.hozzaadNyersanyag(ny);
	}
	
	public void Robbanas() {
		Halal();
	}
	
	public void Meghal() {
		a.torolSzereplo(this);
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
