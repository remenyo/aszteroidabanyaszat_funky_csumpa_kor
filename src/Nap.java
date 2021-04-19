package src;

import java.util.ArrayList;

/**
 * Aszteroidákat ismer akiken meghívja a napvihart valamilyen valószínűséggel egy kör
 * előrejelzéssel, most tesztelés miatt egyből napvihart hív.
 */
public class Nap implements Leptetheto {
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>(); //Nap körüli aszteroidák
	private ArrayList<Aszteroida> napviharravarok = new ArrayList<Aszteroida>(); // Akiken meg lesz hívva a napvihar
	private boolean napviharfolyamatban = false; // Azt jelzi, hogy az előrejelzés van-e
	/**
	 * Nap lépése, az aszteroidak 5% at kiválasztja és szól nekik madj, következő körben meghívja rajtuk.
	 */
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
				napviharfolyamatban=true;
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


	/**
	 * A naphoz hozzáadunk egy aszteroida listát
	 * 
	 * @param a aszteroida lista
	 */
	public void hozzaadAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.add(a);
	}

	/**
	 * A nap adatait adja vissza.
	 * @return adatai
	 */
	public String toString() {
		return Integer.toString(aszteroidak.size());
	}


	@Override // teszt miatt nem fontos
	public Boolean lepette() {
		return null;
	}


	@Override // teszt miatt nem fontos
	public void resetLepett() {

	}
}
