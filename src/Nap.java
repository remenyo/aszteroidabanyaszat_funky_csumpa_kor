package src;

import java.util.ArrayList;

/**
 * Aszteroidákat ismer akiken meghívja a napvihart valamilyen valószínûséggel egy kör
 * elõrejelzéssel, most tesztelés miatt egybõl napvihart hív.
 */
public class Nap implements Leptetheto {
	// TODO: a nap globális? akkor ez is legyen singleton
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>(); // Nap körüli
	private ArrayList<Aszteroida> napviharravarok = new ArrayList<Aszteroida>(); //Akiken következõ körben napvihar lesz																			// aszteroidákat
																				// tároló lista
	private boolean napviharfolyamatban = false; // Azt jelzi, hogy az elõrejelzés van-e, ha igaz akkor
											// indul a napvihar ha hamis akkor nem
											// most teszteléshez nem használjuk.
	// Ez a függvény felel a napvihar meghívásáért a naphoz tartozó aszteroidán.

	public void Lepes() {
		Log.call();
		if(napviharfolyamatban) {
			if(RandomUtils.randomIntHatarokKozott(0, 100)<=5) {
				napviharravarok.clear();
				
			}
		}
	}


	/**
	 * Törli a naptól egy aszteroidát.
	 * 
	 * @param a törlendõ aszteroida
	 */
	public void torolAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.remove(a);
	}

	// A lépés ezt hívná meg, hogy az elõrejelzés alapján napvihart generáljon
	// de most teszteléshez nem használjuk.
	// TODO napvihar
	private void Napvihar() {
		Log.call();
	}


	/**
	 * A naphoz hozzáadunk egy aszteroida listát
	 * 
	 * @param a aszteroida lista
	 */
	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		Log.call();
		aszteroidak = a;
	}
}
