package src;

import java.util.ArrayList;

public class Jatek {

	public static final Jatek INSTANCE = new Jatek();
	public static int telepesszam;
	public static ArrayList<Leptetheto> leptethetok;

	public static final boolean LOG_FUNCTION_CALLS = true;
	public static final boolean LOG_CONSTRUCTORS = true;
	public static final int MIN_SZEN = 3;
	public static final int MIN_URAN = 3;
	public static final int MIN_VIZJEG = 3;
	public static final int MIN_VAS = 3;
	public static final float ROBOT_MOZOG_FUR_ARANY = 3;

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	public static void Kor() {
		Log.call();
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}

	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	public static void telepesMeghal() {
		Log.call();
		telepesszam--;
	}

	public static void jatekVegeNyert() {
		Log.call();
		System.out.println("Gratulálunk nyertél!! :)");
		System.exit(0);
	}

	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratulálunk vesztettél !! :)");
		System.exit(0);
	}

	public static void jatekInditas() {
		Log.call();
	}

}
