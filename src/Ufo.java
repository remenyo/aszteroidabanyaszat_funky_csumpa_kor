package src;

public class Ufo extends Szereplo {

	Ufo() {
		super();
		Log.ctor();
	}

	public void Lepes() {
		Log.call();
		Boolean sikeres = Banyaszat();
		if (sikeres) {
			Integer sorszam =
					RandomUtils.randomIntHatarokKozott(0, aszteroida.getSzomszedok().size() - 1);
			Mozgas(sorszam);
		}
	}

	public Boolean Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (temp != null) {
			temp.ellenorizVesztett();
			return true;
		}
		return false;
	}

	public String toString() {
		return Szkeleton.getID(aszteroida) + ":"
				+ String.valueOf(lepett); 
	}

}
