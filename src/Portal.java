package src;

/**
 * aktiv - A portalon keresztül lehet e utazni birtokos - A telepes aki a portált birtokolni par -A
 * portál párja vegpont -az aszteroida, amin a portál elhelyezkedik megkergult - a portál
 * megkerg�lts�g�t jellemzi
 */
public class Portal extends Hely implements Leptetheto {
	private Boolean aktiv = false;
	private Boolean megkergult = false;
	private Telepes birtokos;
	private Portal par;
	private Aszteroida vegpont;
	private Boolean lepett = false;

	/**
	 * Meghívja az aszteroid�n és a párján a beszippantot.
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
	 * Be�ll�tja a param�terk�nt kapott aszteroid�t a portál v�gpontjának
	 * 
	 * @param a a be�ll�tand� v�gpont
	 */
	public void beallitVegpont(Aszteroida a) {
		Log.call();
		vegpont = a; // megt�rt�nik a be�ll�tés
		birtokos.torolPortal(this); // T�bb� nem fogja birtokolni telepes ezt a portált
		birtokos = null; // A portál sem t�rolja t�bb� az �t birtokl� telepest
		vegpont.hozzaadSzomszed(this); // Az aszteroid�n be�ll�tésra ker�l a portál

		mukodesbeHelyezes(a);
	}


	/**
	 * A portál párj�val val� ésszerendelés
	 * 
	 * @param p a mésik portál
	 */
	public void beallitPar(Portal p) {
		Log.call();
		par = p;
	}


	/**
	 * Ha a portálnak és a párjának is van v�gpontja, akkor enged�lyezi az utazést k�t portálpár
	 * k�z�tt
	 * 
	 * @param a
	 */
	private void mukodesbeHelyezes(Aszteroida a) {
		Log.call();
		if (par.getVegpont() != null) { // ha nincs, akkor nem lehet
										// utazni a k�t portál k�z�tt
			// Mindk�t portált utazhat�v� kell tenni
			beallitAktiv();
			par.beallitAktiv();
		}

	}

	/**
	 * Megsemmis�ti a portált
	 */
	private void Beszippant() {
		Log.call();
		if (birtokos != null)
			// Ha telepesn�l van a portál, akkor elt�vol�tja azt az �rhaj�j�b�l
			birtokos.torolPortal(this);
		if (vegpont != null)
			// Ha egy aszteroid�n van a portál, akkor elt�vol�tja azt a felsz�n�r�l
			vegpont.torolSzomszed(this);

	}

	/**
	 * Ha a portál meg van kerg�lve, akkor minden k�rben mozog egyet a v�gpontja valamelyik
	 * szomsz�dj�ra
	 */
	public void Lepes() {
		Log.call();
		if (megkergult) {
			int szomszedhossz = vegpont.getSzomszedok().size();
			Mozgas(RandomUtils.randomIntHatarokKozott(0, szomszedhossz));
		}

	}

	/**
	 * A portál mozgésa aszteroid�k k�z�tt
	 * 
	 * @param sorszam - a v�gpontjának erre a sorsz�m� szomsz�dj�ra mozog
	 */
	public void Mozgas(int sorszam) {
		Log.call();
		Hely uj = vegpont.getSzomszed(sorszam);
		vegpont.torolSzomszed(this);
		uj.utazasHely(this);

	}

	/**
	 * Meghívja a párján a teleportálés f�ggv�ny�t a megadott param�terrel
	 *
	 * @param sz - Szereplo, akit utaztat
	 */
	public void Utazas(Szereplo sz) {
		Log.call();
		if (aktiv)
			par.teleportalas(sz);
	}


	/**
	 * A param�terk�nt kapott szerepl�t utaztatja a v�gpontj�ra
	 *
	 * @param sz - Szereplo, akit elteleportál a v�gpon
	 */
	public void teleportalas(Szereplo sz) {
		Log.call();
		vegpont.Utazas(sz);
	}

	/**
	 * Elhelyezi a párján a param�ter�l kapott Hely-et
	 *
	 * @param hely - Hely, akit utaztat
	 */
	public void utazasHely(Hely hely) {
		Log.call();
		if (aktiv)
			par.getVegpont().hozzaadSzomszed(hely);
	}

	public void szomszedNapvihar() {
		Log.call();
		megkergult = true;
		Jatek.hozzaadLeptetheto(this);
	}


	/**
	 * �t birtokl� telepes be�ll�tésa (teszthez)
	 * 
	 * @param t - portál birtokosa
	 */
	public void setBirtokos(Telepes t) {
		Log.call();
		birtokos = t;
	}


	/**
	 * Annak az aszteroid�nak a be�ll�tésa, amin a portál el fog helyezkedni (teszt)
	 * 
	 * @param a - az aszteroida, amin a portál el fog helyezkedni
	 */
	public void setVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
	}

	public String toString() { 
		String kimenet = aktiv.toString() + ":" ; 
		if(birtokos!=null)kimenet += Szkeleton.getID(birtokos);else {kimenet+="null";} 
		kimenet += ":" ;
		if(par!=null)kimenet += Szkeleton.getID(par); else {kimenet+="null";}
				kimenet += ":" ;
		if( vegpont != null ) kimenet+= Szkeleton.getID(vegpont); else{kimenet+="null";}
		kimenet +=  ":" + megkergult.toString() + ":"
				+ String.valueOf(lepett);
		return kimenet;/*
											 * + "" + (char) 13 + (char) 10;
											 */ // TODO nem kell ujsor
	}

	 @Override
		public Boolean lepette() {
			if(lepett) {
				return true;
			}else {
				lepett = true;
				return false;
			}
		}
	    @Override
		public void resetLepett() {
			lepett = false;
		}

	// teszt miatt
	public Aszteroida getAszteroida() {
		return vegpont;
	}
}
