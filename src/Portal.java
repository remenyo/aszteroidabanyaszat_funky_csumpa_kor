package src;

/**
 * aktiv - A portalon keresztül lehet e utazni birtokos - A telepes aki a
 * portált birtokolni par -A portál párja vegpont -az aszteroida, amin a portál
 * elhelyezkedik megkergult - a portál megkergültségét jellemzi
 */
public class Portal extends Hely implements Leptetheto {
	private Boolean aktiv = false;
	private Boolean megkergult = false;
	private Telepes birtokos;
	private Portal par;
	private Aszteroida vegpont;
	private Boolean lepett = false;

	/**
	 * Meghívja az aszteroidán és a párján a beszippantot.
	 */
	public void Robbanas() {
		Log.call();
		par.Beszippant();
		Beszippant();
	}

	/**
	 * Utazhatóvá teszi a portalt
	 */
	void beallitAktiv() {
		Log.call();
		aktiv = true;
	}

	public Aszteroida szomszedosAszteroida() {
		return par.getVegpont();
	}

	/**
	 * Visszaadja azt az aszteroidát, amin a portal elhelyezkedik
	 * 
	 * @return Aszteroida az aszteroida, amin a portal elhelyezkedik.
	 */
	public Aszteroida getVegpont() {
		Log.call();
		return vegpont;
	}

	/**
	 * Beállítja a paraméterként kapott aszteroidát a portál végpontjának
	 * 
	 * @param a a beállítandó végpont
	 */
	public void beallitVegpont(Aszteroida a) {
		Log.call();
		vegpont = a; // megtörténik a beállítás
		birtokos.torolPortal(this); // Többé nem fogja birtokolni telepes ezt a portált
		birtokos = null; // A portál sem tárolja többé az őt birtokló telepest
		vegpont.hozzaadSzomszed(this); // Az aszteroidán beállításra kerül a portál

		mukodesbeHelyezes(a);
	}

	/**
	 * A portál párjával való összerendelés
	 * 
	 * @param p a mésik portál
	 */
	public void beallitPar(Portal p) {
		Log.call();
		par = p;
	}

	/**
	 * Ha a portálnak és a párjának is van végpontja, akkor engedélyezi az utazást
	 * két portálpár között
	 * 
	 * @param a
	 */
	private void mukodesbeHelyezes(Aszteroida a) {
		Log.call();
		if (par.getVegpont() != null) { // ha nincs, akkor nem lehet
										// utazni a két portál között
			// Mindkét portált utazhatóvá kell tenni
			beallitAktiv();
			par.beallitAktiv();
		}

	}

	/**
	 * Megsemmisíti a portált
	 */
	private void Beszippant() {
		Log.call();
		if (birtokos != null)
			// Ha telepesnél van a portál, akkor eltávolítja azt az űrhajójából
			birtokos.torolPortal(this);
		if (vegpont != null)
			// Ha egy aszteroidán van a portál, akkor eltávolítja azt a felszínéről
			vegpont.torolSzomszed(this);

	}

	/**
	 * Ha a portál meg van kergülve, akkor minden körben mozog egyet a végpontja
	 * valamelyik szomszédjára
	 */
	public void Lepes() {
		Log.call();
		if (megkergult) {
			int szomszedhossz = vegpont.getSzomszedok().size();
			Mozgas(RandomUtils.randomIntHatarokKozott(0, szomszedhossz));
		}
		synchronized (Jatek.lepesKesz) {
			Jatek.lepesKesz.notify();
		}
	}

	/**
	 * A portál mozgása aszteroidák között
	 * 
	 * @param sorszam - a végpontjának erre a sorszámú szomszédjára mozog
	 */
	public void Mozgas(Integer sorszam) {
		Log.call();
		Hely uj = vegpont.getSzomszed(sorszam);
		vegpont.torolSzomszed(this);
		uj.utazasHely(this);

	}

	/**
	 * Meghívja a párján a teleportálés függvényét a megadott paraméterrel
	 *
	 * @param sz - Szereplo, akit utaztat
	 */
	public void Utazas(Szereplo sz) {
		Log.call();
		if (aktiv)
			par.teleportalas(sz);
	}

	/**
	 * A paraméterként kapott szereplőt utaztatja a végpontjára
	 *
	 * @param sz - Szereplo, akit elteleportál a végpont
	 */
	public void teleportalas(Szereplo sz) {
		Log.call();
		vegpont.Utazas(sz);
	}

	/**
	 * Elhelyezi a párján a paraméterként kapott Hely-et
	 *
	 * @param hely - Hely, akit utaztat
	 */
	public void utazasHely(Portal hely) {
		Log.call();
		if (aktiv)
			par.getVegpont().utazasHely(hely);

	}

	/**
	 * Megkergültté teszi a portált, és hozzáadja a léptethetők közé
	 */
	public void szomszedNapvihar() {
		Log.call();
		megkergult = true;
		Jatek.hozzaadLeptetheto(this);
	}

	/**
	 * Őt birtokló telepes beállítása (teszthez)
	 * 
	 * @param t - portál birtokosa
	 */
	public void setBirtokos(Telepes t) {
		Log.call();
		birtokos = t;
	}

	/**
	 * Annak az aszteroidának a beállítása, amin a portál el fog helyezkedni (teszt)
	 * 
	 * @param a - az aszteroida, amin a portál el fog helyezkedni
	 */
	public void setVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
	}

	/**
	 * Portál a kimeneten
	 * 
	 * @return - A portál attribútumait adja vissza szövegként
	 */
	public String toString() {
		String kimenet = aktiv.toString() + ":";
		if (birtokos != null)
			kimenet += Szkeleton.getID(birtokos);
		else {
			kimenet += "null";
		}
		kimenet += ":";
		if (par != null)
			kimenet += Szkeleton.getID(par);
		else {
			kimenet += "null";
		}
		kimenet += ":";
		if (vegpont != null)
			kimenet += Szkeleton.getID(vegpont);
		else {
			kimenet += "null";
		}
		kimenet += ":" + megkergult.toString() + ":" + String.valueOf(lepett);
		return kimenet;

	}

	/**
	 * Portál lépésének az állapotát ellenőrzi a körben
	 */
	@Override
	public Boolean lepette() {
		if (lepett) {
			return true;
		} else {
			lepett = true;
			return false;
		}
	}

	/**
	 * lépés helyreállítása
	 */
	@Override
	public void resetLepett() {
		lepett = false;
	}

	/**
	 * Aszteroida lekérdezése
	 */
	// teszt miatt
	public Aszteroida getAszteroida() {
		return vegpont;
	}

	/**
	 * aktív attribútum átállítása
	 * 
	 * @param bl - amire az attribútum értéke változzon
	 */
	public void setAktiv(Boolean bl) {
		aktiv = bl;
	}
}
