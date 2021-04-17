package src;

import java.util.ArrayList;

/**
 * Aszteroidákat ismer akiken meghívja a napvihart valamilyen valószínűséggel egy kör
 * előrejelzéssel, most tesztelés miatt egyből napvihart hív.
 */
public class Nap implements Leptetheto {
	// TODO: a nap globális? akkor ez is legyen singleton
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>(); // Nap körüli
	private ArrayList<Aszteroida> napviharravarok = new ArrayList<Aszteroida>(); // Akiken következő
																					// körben
																					// napvihar lesz
																					// //
																					// aszteroidákat
																					// tároló lista
	private boolean napviharfolyamatban = false; // Azt jelzi, hogy az előrejelzés van-e, ha igaz
													// akkor
	// indul a napvihar ha hamis akkor nem
	// most teszteléshez nem használjuk.
	// Ez a függvény felel a napvihar meghívásáért a naphoz tartozó aszteroidán.

	public void Lepes() {
		Log.call();
		if (!napviharfolyamatban) {
			if (RandomUtils.randomIntHatarokKozott(0, 100) <= 5) {
				napviharravarok.clear();
				for (int i = 0; i < aszteroidak.size() / 2; i++) {
					Aszteroida randomNapviharravaro = aszteroidak
							.get(RandomUtils.randomIntHatarokKozott(0, aszteroidak.size() - 1));
					if (!napviharravarok.contains(randomNapviharravaro)) {
						napviharravarok.add(randomNapviharravaro);
					}
				}
				for (Aszteroida a : napviharravarok) {
					a.setElorejelzesvan();
				}
			}
		} else {
			for (Aszteroida a : napviharravarok) {
				a.Napvihar();
			}
			napviharfolyamatban = false;
		}
	}


	/**
	 * Törli a naptól egy aszteroidát.
	 * 
	 * @param a törlendő aszteroida
	 */
	public void torolAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.remove(a);
	}

	// A lépés ezt hívná meg, hogy az előrejelzés alapján napvihart generáljon
	// de most teszteléshez nem használjuk.
	// TODO napvihar
	private void Napvihar() {
		Log.call(); // kell-e?
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

	public String toString() {
		return Integer.toString(aszteroidak.size()) /* + "" + (char) 13 + (char) 10 */;
	}


	@Override // teszt miatt nem fontos
	public Boolean lepette() {
		return null;
	}


	@Override // teszt miatt nem fontos
	public void resetLepett() {

	}
}
