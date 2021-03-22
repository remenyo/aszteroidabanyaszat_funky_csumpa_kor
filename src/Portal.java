package src;

public class Portal extends Hely {
	private Boolean aktiv = false; //A portalon keresztül lehet e utazni
	private Telepes birtokos; //A telepes aki a portált birtokolni
	private Portal par; //A portál párja
	private Aszteroida vegpont; //-az aszteroida, amin a portál elhelyezkedik

	//Meghívja az aszteroidán és a párján a beszippantot
	public void Robbanas() { 
		Log.call();
		par.Beszippant();
		Beszippant();
	}

	//Utazhatóvá teszi a portalt
	void beallitAktiv() {
		Log.call();
		aktiv = true;
	}

	//Visszaadja azt az aszteroidát, amin a portal elhelyezkedik
	public Aszteroida getVegpont() {
		Log.call();
		return vegpont;
	}

	//Beállítja a paraméterként kapott aszteroidát a portál végpontjának
	public void beallitVegpont(Aszteroida a) {
		Log.call();
		vegpont = a; //megtörténik a beállítás
		birtokos.torolPortal(this); //Többé nem fogja birtokolni telepes ezt a portált
		birtokos = null; //A portál sem tárolja többé az õt birtokló telepest
		vegpont.hozzaadSzomszed(this); //Az aszteroidán beállításra kerül a portál

		mukodesbeHelyezes(a);
	}

	public void beallitPar(Portal p) {
		Log.call();
		par = p;
	}

	//Ha a portálnak és a párjának is van végpontja, akkor engedélyezi az utazást két portálpár között
	private void mukodesbeHelyezes(Aszteroida a) {
		Log.call();
		if (Szkeleton.Kerdes("A portal párjának van végpontja?")) { //ha nincs, akkor nem lehet utazni a két portál között
			//Mindkét portált utazhatóvá kell tenni
			beallitAktiv();
			par.beallitAktiv();
		}

	}

	//Megsemmisíti a portált
	private void Beszippant() {
		Log.call();
		if (Szkeleton.Kerdes("A portalnak van tulajdonosa?"))
			//Ha telepesnél van a portál, akkor eltávolítja azt az ûrhajójából
			birtokos.torolPortal(this);
		if (Szkeleton.Kerdes("A portal rajta van egy aszteroidán?"))
			//Ha egy aszteroidán van a portál, akkor eltávolítja azt a felszínérõl
			vegpont.torolSzomszed(this);

	}

	//Meghívja a párján a teleportálás függvényét a megadott paraméterrel
	public void Utazas(Szereplo sz) {
		Log.call();
		if (Szkeleton.Kerdes("A portal aktív?"))
			par.Teleportalas(sz);
	}

	//A paraméterként kapott szereplõt utaztatja a végpontjára
	public void Teleportalas(Szereplo sz) {
		Log.call();
		vegpont.Utazas(sz);
	}

	//Õt birtokló telepes beállítása (teszthez)
	public void setBirtokos(Telepes t) {
		Log.call();
		birtokos = t;
	}

	//Annak az aszteroidának a beállítása, amin a portál el fog helyezkedni (teszt)
	public void setVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
	}

}
