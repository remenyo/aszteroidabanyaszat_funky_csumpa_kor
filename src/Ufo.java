package src;

public class Ufo extends Szereplo {

	/**
	 * Ufo konstruktora
	 */
	Ufo() {
		super();
		Log.ctor();
	}

	/**
	 * Ufo lép ha tud felvenni nyersanyagot felvesz ha nem akkor random elmozog egy szomszédra
	 */
	public void Lepes() {
		Log.call();
		Boolean sikeres = Banyaszat();
		if (sikeres) {
			Integer sorszam =
					RandomUtils.randomIntHatarokKozott(0, aszteroida.getSzomszedok().size() - 1);
			Mozgas(sorszam);
		}
	}

	/**
	 * Felvesz nyersanyagot az ufo
	 * @return sikerült - true nem sikerült - false
	 */
	public Boolean Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (temp != null) {
			temp.ellenorizVesztett();
			return true;
		}
		return false;
	}

	/**
	 * Ufo adatait adja vissza
	 * @return adatai az ufo nak
	 */
	public String toString() {
		return Szkeleton.getID(aszteroida) + ":"
				+ String.valueOf(lepett); 
	}

}
