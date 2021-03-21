package src;
import java.util.ArrayList;
import java.util.Random;

public class Nap implements Leptetheto{
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>();
	private boolean elorejelzesvan = false;	
	public void Lepes() {
		for (Aszteroida a : aszteroidak) 
		{ 
		    a.Napvihar();
		}
	}
	
	public void torolAszteroida(Aszteroida a) {
		aszteroidak.remove(a);
	}
	
	private void Napvihar() {
		//hat ez ugytunik nem kell de lepesbe belelehet rakni ha akarjuk
	}
	
	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		aszteroidak = a;
	}
}
