package src;

import java.util.ArrayList;
import java.util.Scanner;

public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok = new ArrayList<Nyersanyag>();
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
	private ArrayList<Portal> portal = new ArrayList<Portal>();

	public void Mozgas(int sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	public void Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (Szkeleton.Kerdes("Van az aszteroidában nyersanyag?")) {
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		}
	}

	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.call();
		return nyersanyagok;
	}

	public void Lepes() {
		Log.call();
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		// nemtom hogy itt mizu

	}

	public void epitRobot() {
		Log.call();
		boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok); // ??????????????????????
		if (vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
		}
	}

	public void epitPortal() {
		Log.call();
		if (!Szkeleton.Kerdes("Van a telepesnél portálkapu?")) {
			boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok); // ??????????????????????
			if (vaneleg) {
				Portal p1 = new Portal();
				Portal p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
			}
		}
	}

	public void lehelyezPortal(Portal p) {
		Log.call();
		p.beallitVegpont(aszteroida);
	}

	public void torolPortal(Portal p) {
		Log.call();
		portal.remove(p);
	}

	public void visszarakNyersanyag(Nyersanyag ny) {
		Log.call();
		if (Szkeleton.Kerdes("Üres és kifúrt?")) {
			nyersanyagok.remove(ny);
			aszteroida.hozzaadNyersanyag(ny);
		}

	}

	public void Robbanas() {
		Log.call();
		Meghal();
	}

	public void Meghal() {
		Log.call();
		aszteroida.torolSzereplo(this);
		portal.remove(0);
		nyersanyagok.remove(0);
		Jatek.torolLeptetheto(this);
		Jatek.telepesMeghal();
	}

	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		nyersanyagok.add(ny);
	}

	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		Log.call();
		epiteskoltseg.add(k);
	}

	public void setPortal(Portal p) {
		Log.call();
		portal.add(p);
	}
}
