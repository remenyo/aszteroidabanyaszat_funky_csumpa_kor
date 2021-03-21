package src;

import java.util.ArrayList;

public class Jatek {
	private static Jatek jatek = null;
	int telepesszam;
	ArrayList<Leptetheto> leptethetok;

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	public static Jatek getInstance() {
		if (jatek == null)
			jatek = new Jatek();

		return jatek;
	}

	public void Kor() {
		Log.info("Meghivodott");
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}

	public void torolLeptetheto(Leptetheto l) {
		Log.info("Meghivodott");
		leptethetok.remove(l);
	}

	public void telepesMeghal() {
		Log.info("Meghivodott");
		telepesszam--;
	}

	public void jatekVegeNyert() {
		Log.info("Meghivodott");
		System.out.println("Gratul�lunk nyert�l!! :)");
		System.exit(0);
	}

	public void jatekVegeVesztett() {
		Log.info("Meghivodott");
		System.out.println("Gratul�lunk vesztett�l !! :)");
		System.exit(0);
	}

	public void jatekInditas() {
		Log.info("Meghivodott");
	}
	
}
