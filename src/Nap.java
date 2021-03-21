package src;
import java.util.ArrayList;
import java.util.Random;

public class Nap implements Leptetheto{
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>();
	private boolean elorejelzesvan = false;	
	public void Lepes() {
		Log.info("Meghivodott");
		aszteroidak.get(0).Napvihar();
	}
	
	public void torolAszteroida(Aszteroida a) {
		Log.info("Meghivodott");
		aszteroidak.remove(a);
	}
	
	private void Napvihar() {
		Log.info("Meghivodott");
		//hat ez ugytunik nem kell de lepesbe belelehet rakni ha akarjuk
	}
	
	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		Log.info("Meghivodott");
		aszteroidak = a;
	}
}
