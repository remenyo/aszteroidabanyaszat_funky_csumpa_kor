package src;

import java.util.ArrayList;

/**
 * Aszteroid�kat ismer akiken megh�vja a napvihart valamilyen val�sz�n�s�ggel egy k�r
 * el�rejelz�ssel, most tesztel�s miatt egyb�l napvihart h�v.
 */
public class Nap implements Leptetheto {
	// TODO: a nap glob�lis? akkor ez is legyen singleton
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>(); // Nap k�r�li
	private ArrayList<Aszteroida> napviharravarok = new ArrayList<Aszteroida>(); //Akiken k�vetkez� k�rben napvihar lesz																			// aszteroid�kat
																				// t�rol� lista
	private boolean napviharfolyamatban = false; // Azt jelzi, hogy az el�rejelz�s van-e, ha igaz akkor
											// indul a napvihar ha hamis akkor nem
											// most tesztel�shez nem haszn�ljuk.
	// Ez a f�ggv�ny felel a napvihar megh�v�s��rt a naphoz tartoz� aszteroid�n.

	public void Lepes() {
		Log.call();
		if(napviharfolyamatban) {
			if(RandomUtils.randomIntHatarokKozott(0, 100)<=5) {
				napviharravarok.clear();
				
			}
		}
	}


	/**
	 * T�rli a napt�l egy aszteroid�t.
	 * 
	 * @param a t�rlend� aszteroida
	 */
	public void torolAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.remove(a);
	}

	// A l�p�s ezt h�vn� meg, hogy az el�rejelz�s alapj�n napvihart gener�ljon
	// de most tesztel�shez nem haszn�ljuk.
	// TODO napvihar
	private void Napvihar() {
		Log.call();
	}


	/**
	 * A naphoz hozz�adunk egy aszteroida list�t
	 * 
	 * @param a aszteroida lista
	 */
	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		Log.call();
		aszteroidak = a;
	}
}
