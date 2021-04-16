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

	// a mozgás valószínûsége, ha nem mozog épp a robot akkor fúr
	public static final double ROBOT_MOZGAS_VALOSZINUSEG = 0.7;

	// ha ennél kevesebb telepes marad a játékban
	public static final double MIN_TELEPES_NYERESHEZ = 1;

	public static final Integer JATEKOS_SZAM = 5;
	
	public static final Integer SZOMSZED_SZAM = 8;
	
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


	/**
	 * Törli a paraméterként kapott léptethetõt a listából
	 * 
	 * @param l a törlendõ léptethetõ
	 */
	public static void torolLeptetheto(Leptetheto l) {
		Log.call();
		leptethetok.remove(l);
	}

	/**
	 * Csökkenti a telepesszámot eggyel, és ha már nincs elég akkor meghívja a játék vége vesztett
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
	 * Gratulál a gyõzelemhez és lezárja a progit
	 */
	public static void jatekVegeNyert() {
		Log.call();
		System.out.println("Gratulálunk nyertél!! :)");
		allapot = 1;
	}

	/**
	 * Vereségnél lezárja a progit
	 */
	public static void jatekVegeVesztett() {
		Log.call();
		System.out.println("Gratulálunk vesztettél !! :)");
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
		ArrayList<Aszteroida> atmenetiAszteroidatar = new ArrayList<Aszteroida>();
		for (int i = 0; i < 50; i++) {
			Aszteroida a = new Aszteroida(n); //0 Vas 1 Szén 2 Vizjeg 3 Uran 4 üres
			if(i%5==0) {
				a.setNyersanyag(new Vas());
			}else if(i%5 == 1) {
				a.setNyersanyag(new Szen());
			}else if(i%5 == 2) {
				a.setNyersanyag(new Vizjeg());
			}else if(i%5 == 3) {
				a.setNyersanyag(new Uran());
			}else if(i%5 == 4) {
				a.setNyersanyag(null);
			}
			
			if(i == 0) {
				for (int j = 0; j < JATEKOS_SZAM; j++) {
					Telepes t = new Telepes();
					a.hozzaadSzereplo(t);
					telepesszam++;
					leptethetok.add(t);
				}
			}
			atmenetiAszteroidatar.add(a);
		}
		atmenetiAszteroidatar.get(0).hozzaadSzomszed(atmenetiAszteroidatar.get(atmenetiAszteroidatar.size()-1));
		for(int i =0; i<atmenetiAszteroidatar.size()-1;i++) {
			atmenetiAszteroidatar.get(i).hozzaadSzomszed(atmenetiAszteroidatar.get(i+1));
			atmenetiAszteroidatar.get(i+1).hozzaadSzomszed(atmenetiAszteroidatar.get(i));
			for(int j = 0; j<SZOMSZED_SZAM-2; j++) {
				atmenetiAszteroidatar.get(i).hozzaadSzomszed(atmenetiAszteroidatar.get(RandomUtils.randomIntHatarokKozott(0,atmenetiAszteroidatar.size()-1 )));
			}
		}
		
		n.hozzaadAszteroidak(atmenetiAszteroidatar);
		
		allapot = 0;
		while(allapot == 0) {
			for (Leptetheto leptetheto : leptethetok) {
				leptetheto.Lepes();
				if (allapot!=0) {
					break;
				}
			}
		}
	}

}
