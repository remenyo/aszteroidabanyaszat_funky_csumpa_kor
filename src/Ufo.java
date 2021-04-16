package src;

import java.util.ArrayList;

public class Ufo extends Szereplo {

	Ufo(){
		super();
		Log.ctor();
	}
	
	public void Lepes() {
		Log.call();
		Boolean sikeres = nyersanyagFelvetel();
		if(sikeres) {
			Integer sorszam = RandomUtils.randomIntHatarokKozott(0, aszteroida.getSzomszedok().size());
			Mozgas(sorszam);
		}
	}
	
	public Boolean nyersanyagFelvetel() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if(temp != null) {
			temp.ellenorizVesztett();
			return true;
		}
		return false;
	}
}
