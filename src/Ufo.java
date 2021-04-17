package src;

public class Ufo extends Szereplo {

	Ufo() {
		super();
		Log.ctor();
	}

	public void Lepes() {
		Log.call();
		Boolean sikeres = nyersanyagFelvetel();
		if (sikeres) {
			Integer sorszam =
					RandomUtils.randomIntHatarokKozott(0, aszteroida.getSzomszedok().size() - 1);
			Mozgas(sorszam);
		}
	}

	public Boolean nyersanyagFelvetel() {
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
				+ String.valueOf(lepett); /* + (char) 13 + (char) 10 */
	}

}
