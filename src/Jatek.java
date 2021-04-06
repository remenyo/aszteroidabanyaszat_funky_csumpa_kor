package src;

import java.util.ArrayList;

public class Jatek {

	public static final Jatek INSTANCE = new Jatek();
	public static Integer telepesszam = 0;
	public static ArrayList<Leptetheto> leptethetok;

	public static Integer MIN_SZEN = 3;
	public static Integer MIN_URAN = 3;
	public static Integer MIN_VIZJEG = 3;
	public static Integer MIN_VAS = 3;

	// a mozg�s val�sz�n�s�ge, ha nem mozog �pp a robot akkor f�r
	public static final double ROBOT_MOZGAS_VALOSZINUSEG = 0.7;

	// ha enn�l kevesebb telepes marad a j�t�kban
	public static final double MIN_TELEPES_NYERESHEZ = 1;

	private Jatek() {
		leptethetok = new ArrayList<Leptetheto>();
	}

	// Nem haszn�ljuk a tesztben, kezdetleges K�r
	public static void Kor() {
		Log.call();
		for (Leptetheto leptetheto : leptethetok) {
			leptetheto.Lepes();
		}
	}


	/**
	 * T�rli a param�terk�nt kapott l�ptethet�t a list�b�l
	 * 
	 * @param l a t�rlend� l�ptethet�
	 */
	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	/**
	 * Cs�kkenti a telepessz�mot eggyel, �s ha m�r nincs el�g akkor megh�vja a j�t�k v�ge vesztett
	 * fv.t
	 */
	public static void telepesMeghal() {
		Log.call();
		telepesszam--;
		Log.info(telepesszam + " telepes maradt.");
		if (telepesszam < MIN_TELEPES_NYERESHEZ) {
			jatekVegeVesztett();
		}

	}

	/**
	 * Gratul�l a gy�zelemhez �s lez�rja a progit
	 */
	public static void jatekVegeNyert() {
		Log.call();
		System.out.println("Gratul�lunk nyert�l!! :)");
		System.exit(0);
	}

	/**
	 * Veres�gn�l lez�rja a progit
	 */
	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratul�lunk vesztett�l !! :)");
		System.exit(0);
	}

	public static void jatekInditas() {
		Log.call();
	}

}
