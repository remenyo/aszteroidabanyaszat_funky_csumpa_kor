package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok = new ArrayList<Nyersanyag>();
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
	private ArrayList<Portal> portal = new ArrayList<Portal>();

	public void Mozgas(int sorszam) {
		Log.info("Meghivodott");
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	public void Banyaszat() {
		Log.info("Meghivodott");
		Nyersanyag temp = aszteroida.Banyaszat();
		if (Szkeleton.Kerdes("Van az aszteroidában nyersanyag?")) {
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		}
	}

	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.info("Meghivodott");
		return nyersanyagok;
	}

	public void Lepes() {
		Log.info("Meghivodott");
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		// nemtom hogy itt mizu

	}

	public void epitRobot() {
		Log.info("Meghivodott");
		boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok); // ??????????????????????
		if (vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
		}
	}

	public void epitPortal() {
		Log.info("Meghivodott");
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
		Log.info("Meghivodott");
		p.beallitVegpont(aszteroida);
	}

	public void torolPortal(Portal p) {
		Log.info("Meghivodott");
		portal.remove(p);
	}

	public void visszarakNyersanyag(Nyersanyag ny) {
		Log.info("Meghivodott");
		if (Szkeleton.Kerdes("Üres és kifúrt?")) {
			aszteroida.hozzaadNyersanyag(ny);
		}

	}

	public void Robbanas() {
		Log.info("Meghivodott");
		Meghal();
	}

	public void Meghal() {
		Log.info("Meghivodott");
		aszteroida.torolSzereplo(this);
		for (Portal p : portal) {
			p.Robbanas();
		}
		for (Nyersanyag ny : nyersanyagok) {
			ny.Robbanas();
		}
		Jatek.getInstance().torolLeptetheto(this);
		Jatek.getInstance().telepesMeghal();
	}

	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.info("Meghivodott");
		nyersanyagok.add(ny);
	}

	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		Log.info("Meghivodott");
		epiteskoltseg.add(k);
	}
	
	public void setPortal(Portal p) {
		Log.info("Meghivodott");
		portal.add(p);
	}
}
