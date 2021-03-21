package src;

import java.util.ArrayList;

public class Nap implements Leptetheto {
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>();
	private boolean elorejelzesvan = false;

	public void Lepes() {
		Log.call();
		aszteroidak.get(0).Napvihar();
	}

	public void torolAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.remove(a);
	}

	private void Napvihar() {
		Log.call();
		// hat ez ugytunik nem kell de lepesbe belelehet rakni ha akarjuk
	}

	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		Log.call();
		aszteroidak = a;
	}
}
