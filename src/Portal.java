package src;
/**
 * aktiv - A portalon kereszt�l lehet e utazni
 * birtokos - A telepes aki a port�lt birtokolni
 * par -A port�l p�rja
 * vegpont -az aszteroida, amin a port�l elhelyezkedik
 * megkergult - a portál megkergültségét jellemzi
 */
public class Portal extends Hely implements Leptetheto{
	private Boolean aktiv = false;
	private Boolean megkergult = false;
	private Telepes birtokos;
	private Portal par;
	private Aszteroida vegpont;

	/**
	 * Megh�vja az aszteroid�n �s a p�rj�n a beszippantot.
	 */
	public void Robbanas() {
		par.Beszippant();
		Beszippant();
	}

	/**
	 * Utazhat�v� teszi a portalt
	 */
	void beallitAktiv() {
		aktiv = true;
	}


	/**
	 * Visszaadja azt az aszteroid�t, amin a portal elhelyezkedik
	 * 
	 * @return Aszteroida az aszteroida, amin a portal elhelyezkedik.
	 */
	public Aszteroida getVegpont() {
		return vegpont;
	}


	/**
	 * Be�ll�tja a param�terk�nt kapott aszteroid�t a port�l v�gpontj�nak
	 * 
	 * @param a a be�ll�tand� v�gpont
	 */
	public void beallitVegpont(Aszteroida a) {
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
		par = p;
	}


	/**
	 * Ha a port�lnak �s a p�rj�nak is van v�gpontja, akkor enged�lyezi az utaz�st k�t port�lp�r
	 * k�z�tt
	 * 
	 * @param a
	 */
	private void mukodesbeHelyezes(Aszteroida a) {
		if (par.getVegpont()!=null) { // ha nincs, akkor nem lehet
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
		if (birtokos!=null)
			// Ha telepesn�l van a port�l, akkor elt�vol�tja azt az �rhaj�j�b�l
			birtokos.torolPortal(this);
		if (vegpont!=null)
			// Ha egy aszteroid�n van a port�l, akkor elt�vol�tja azt a felsz�n�r�l
			vegpont.torolSzomszed(this);

	}

	/**
	 * Ha a portál meg van kergülve, akkor minden körben mozog egyet a végpontja valamelyik szomszédjára
	 */
	public void Lepes(){
		if(megkergult){
			int szomszedhossz = vegpont.getSzomszedok().size();
			Mozgas(RandomUtils.randomIntHatarokKozott(0, szomszedhossz));
		}

	}

	/**
	 * A portál mozgása aszteroidák között
	 * @param sorszam - a végpontjának erre a sorszámú szomszédjára mozog
	 */
	public void Mozgas(int sorszam){
		Hely uj= vegpont.getSzomszed(sorszam);
		vegpont.torolSzomszed(this);
		uj.utazasHely(this);

	}

	/**
	 * Megh�vja a p�rj�n a teleport�l�s f�ggv�ny�t a megadott param�terrel
	 *
	 * @param sz - Szereplo, akit utaztat
	 */
	public void Utazas(Szereplo sz) {
		if (aktiv)
			par.teleportalas(sz);
	}


	/**
	 * A param�terk�nt kapott szerepl�t utaztatja a v�gpontj�ra
	 *
	 * @param sz - Szereplo, akit elteleportál a végpon
	 */
	public void teleportalas(Szereplo sz) {
		vegpont.Utazas(sz);
	}

	/**
	 * Elhelyezi a párján a paraméterül kapott Hely-et
	 *
	 * @param hely - Hely, akit utaztat
	 */
	public void utazasHely(Hely hely){
		if (aktiv)
			par.getVegpont().hozzaadSzomszed(hely);
	}

	public void szomszedNapvihar(){
		megkergult=true;
		Jatek.hozzaadLeptetheto(this);
	}


	/**
	 * �t birtokl� telepes be�ll�t�sa (teszthez)
	 * 
	 * @param t - portál birtokosa
	 */
	public void setBirtokos(Telepes t) {
		birtokos = t;
	}


	/**
	 * Annak az aszteroid�nak a be�ll�t�sa, amin a port�l el fog helyezkedni (teszt)
	 * 
	 * @param a - az aszteroida, amin a portál el fog helyezkedni
	 */
	public void setVegpont(Aszteroida a) {
		vegpont = a;
	}

}
