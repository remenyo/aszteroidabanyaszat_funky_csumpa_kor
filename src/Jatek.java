package src;

import java.util.ArrayList;
import java.util.Iterator;

public class Jatek {

	public static final Jatek INSTANCE = new Jatek();
	public static Integer telepesszam = 0;
	public static Integer allapot = 0;
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
		allapot = 1;
	}

	/**
	 * Veres�gn�l lez�rja a progit
	 */
	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratul�lunk vesztett�l !! :)");
		allapot = -1;
	}
	
	public static void hozzaadLeptetheto(Leptetheto l){
		Log.call();
		leptethetok.add(l);
	}
	
	//TODO kivonni a nyersanyagokat, Aszteroida konstruktora
	public static void jatekInditas() {
		Log.call();
		NyersanyagKoltseg RobothozNyersanyag = new NyersanyagKoltseg();
		NyersanyagKoltseg PortalhozNyersanyag = new NyersanyagKoltseg();
		NyersanyagKoltseg UrbazishozNyersanyag = new NyersanyagKoltseg();
		RobothozNyersanyag.hozzaadNyersanyag(new Szen());
		RobothozNyersanyag.hozzaadNyersanyag(new Vas());
		RobothozNyersanyag.hozzaadNyersanyag(new Uran());
		
		PortalhozNyersanyag.hozzaadNyersanyag(new Uran());
		PortalhozNyersanyag.hozzaadNyersanyag(new Vas());
		PortalhozNyersanyag.hozzaadNyersanyag(new Vas());
		PortalhozNyersanyag.hozzaadNyersanyag(new Vizjeg());
		
		for(int i = 0; i<3;i++) {
			UrbazishozNyersanyag.hozzaadNyersanyag(new Vas());
			UrbazishozNyersanyag.hozzaadNyersanyag(new Szen());
			UrbazishozNyersanyag.hozzaadNyersanyag(new Vizjeg());
			UrbazishozNyersanyag.hozzaadNyersanyag(new Uran());
		}
		
		Telepes.hozzaadKoltseg(RobothozNyersanyag);
		Telepes.hozzaadKoltseg(PortalhozNyersanyag);
		Aszteroida.hozzaadUrbazisKoltseg(UrbazishozNyersanyag);
		
		Nap n = new Nap();
		leptethetok.add(n);
		for (int i = 0; i < 50; i++) {
			Aszteroida a = new Aszteroida();
			//itt ezt majd 5 kor
		}
		allapot = 0;
		while(allapot == 0) {
			Kor();
		}
	}

}
