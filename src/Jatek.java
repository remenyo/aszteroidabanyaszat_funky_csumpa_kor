package src;

import java.util.ArrayList;

public class Jatek {

	public static final Jatek INSTANCE = new Jatek();
	public static int telepesszam=0;
	public static ArrayList<Leptetheto> leptethetok;

	public static final boolean LOG_FUNCTION_CALLS = true;
	public static final boolean LOG_CONSTRUCTORS = true;
	public static final int LOG_LEVEL = 4; // -1 = semmi | 0 = ctor �s call, ha enged�lyezve vannak,
											// 1 = csak error | 2 = 1+warn | 3 = 2+info | 4 = 3+debug
	public static final int MIN_SZEN = 3;
	public static final int MIN_URAN = 3;
	public static final int MIN_VIZJEG = 3;
	public static final int MIN_VAS = 3;

	public static final float ROBOT_MOZOG_FUR_ARANY = 3;
	public static final float MIN_TELEPES_NYERESHEZ = 1;

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	//Nem haszn�ljuk a tesztben, kezdetleges K�r
	public static void Kor() {
		Log.call();
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}

	// T�rli a param�terk�nt kapott l�ptethet�t a list�b�l
	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	// Cs�kkenti a telepessz�mot eggyel, �s ha m�r nincs el�g akkor megh�vja a j�t�k v�ge vesztett fv.t
	public static void telepesMeghal() {
		Log.call();
		telepesszam--;
		Log.info(telepesszam + " telepes maradt.");
		if (telepesszam < MIN_TELEPES_NYERESHEZ) {
			jatekVegeVesztett();
		}

	}

	// Gratul�l a gy�zelemhez �s lez�rja a progit
	public static void jatekVegeNyert() {
		Log.call();
		System.out.println("Gratul�lunk nyert�l!! :)");
		System.exit(0);
	}

	// Veres�gn�l lez�rja a progit
	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratul�lunk vesztett�l !! :)");
		System.exit(0);
	}

	
	public static void jatekInditas() {
		Log.call();
	}

}
