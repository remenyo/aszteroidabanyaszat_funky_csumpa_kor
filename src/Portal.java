package src;

// TODO argumentum kommentek
public class Portal extends Hely {
	private Boolean aktiv = false; // A portalon kereszt�l lehet e utazni
	private Telepes birtokos; // A telepes aki a port�lt birtokolni
	private Portal par; // A port�l p�rja
	private Aszteroida vegpont; // -az aszteroida, amin a port�l elhelyezkedik

	/**
	 * Megh�vja az aszteroid�n �s a p�rj�n a beszippantot.
	 */
	public void Robbanas() {
		Log.call();
		par.Beszippant();
		Beszippant();
	}

	/**
	 * Utazhat�v� teszi a portalt
	 */
	void beallitAktiv() {
		Log.call();
		aktiv = true;
	}


	/**
	 * Visszaadja azt az aszteroid�t, amin a portal elhelyezkedik
	 * 
	 * @return Aszteroida az aszteroida, amin a portal elhelyezkedik.
	 */
	public Aszteroida getVegpont() {
		Log.call();
		return vegpont;
	}


	/**
	 * Be�ll�tja a param�terk�nt kapott aszteroid�t a port�l v�gpontj�nak
	 * 
	 * @param a a be�ll�tand� v�gpont
	 */
	public void beallitVegpont(Aszteroida a) {
		Log.call();
		vegpont = a; // megt�rt�nik a be�ll�t�s
		birtokos.torolPortal(this); // T�bb� nem fogja birtokolni telepes ezt a port�lt
		birtokos = null; // A port�l sem t�rolja t�bb� az �t birtokl� telepest
		vegpont.hozzaadSzomszed(this); // Az aszteroid�n be�ll�t�sra ker�l a port�l

		mukodesbeHelyezes(a);
	}


	/**
	 * A port�l p�rj�val val� �sszerendel�s
	 * 
	 * @param p a m�sik port�l
	 */
	public void beallitPar(Portal p) {
		Log.call();
		par = p;
	}


	/**
	 * Ha a port�lnak �s a p�rj�nak is van v�gpontja, akkor enged�lyezi az utaz�st k�t port�lp�r
	 * k�z�tt
	 * 
	 * @param a
	 */
	private void mukodesbeHelyezes(Aszteroida a) {
		Log.call();
		if (Cin.getBool("A portal p�rj�nak van v�gpontja?")) { // ha nincs, akkor nem lehet
																// utazni a k�t port�l k�z�tt
			// Mindk�t port�lt utazhat�v� kell tenni
			beallitAktiv();
			par.beallitAktiv();
		}

	}

	/**
	 * Megsemmis�ti a port�lt
	 */
	private void Beszippant() {
		Log.call();
		if (Cin.getBool("A portalnak van tulajdonosa?"))
			// Ha telepesn�l van a port�l, akkor elt�vol�tja azt az �rhaj�j�b�l
			birtokos.torolPortal(this);
		if (Cin.getBool("A portal rajta van egy aszteroid�n?"))
			// Ha egy aszteroid�n van a port�l, akkor elt�vol�tja azt a felsz�n�r�l
			vegpont.torolSzomszed(this);

	}


	/**
	 * Megh�vja a p�rj�n a teleport�l�s f�ggv�ny�t a megadott param�terrel
	 * 
	 * @param sz
	 */
	public void Utazas(Szereplo sz) {
		Log.call();
		if (Cin.getBool("A portal akt�v?"))
			par.Teleportalas(sz);
	}


	/**
	 * A param�terk�nt kapott szerepl�t utaztatja a v�gpontj�ra
	 * 
	 * @param sz
	 */
	public void Teleportalas(Szereplo sz) {
		Log.call();
		vegpont.Utazas(sz);
	}


	/**
	 * �t birtokl� telepes be�ll�t�sa (teszthez)
	 * 
	 * @param t
	 */
	public void setBirtokos(Telepes t) {
		Log.call();
		birtokos = t;
	}


	/**
	 * Annak az aszteroid�nak a be�ll�t�sa, amin a port�l el fog helyezkedni (teszt)
	 * 
	 * @param a
	 */
	public void setVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
	}

}
