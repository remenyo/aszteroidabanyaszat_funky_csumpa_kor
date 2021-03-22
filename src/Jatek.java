package src;

import java.util.ArrayList;

public class Jatek {

	public static final Jatek INSTANCE = new Jatek();
	public static int telepesszam;
	public static ArrayList<Leptetheto> leptethetok;

	public static final int MIN_SZEN = 3;
	public static final int MIN_URAN = 3;
	public static final int MIN_VIZJEG = 3;
	public static final int MIN_VAS = 3;

	public static final float ROBOT_MOZOG_FUR_ARANY = 3;
	public static final float MIN_TELEPES_NYERESHEZ = 1;

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	// Nem használjuk a tesztben, kezdetleges Kör
	public static void Kor() {
		Log.call();
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}

	// Törli a paraméterként kapott léptethetõt a listából
	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	// Csökkenti a telepesszámot eggyel, és ha már nincs elég akkor meghívja a játék
	// vége vesztett fv.t
	public static void telepesMeghal() {
		Log.call();
		telepesszam--;
		Log.info(telepesszam + " telepes maradt.");
		if (telepesszam < MIN_TELEPES_NYERESHEZ) {
			jatekVegeVesztett();
		}

	}

	// Gratulál a gyõzelemhez és lezárja a progit
	public static void jatekVegeNyert() {
		Log.call();
		System.out.println("Gratulálunk nyertél!! :)");
		System.exit(0);
	}

	// Vereségnél lezárja a progit
	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratulálunk vesztettél !! :)");
		System.exit(0);
	}

	public static void jatekInditas() {
		Log.call();
	}

}
