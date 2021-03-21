package src;

public class Portal extends Hely {
	private Boolean aktiv = false;
	private Telepes birtokos;
	private Portal par;
	private Aszteroida vegpont;

	public void Robbanas() {
		Log.call();
		par.Beszippant();
		Beszippant();
	}

	void beallitAktiv() {
		Log.call();
		aktiv = true;
	}

	public Aszteroida getVegpont() {
		Log.call();
		return vegpont;
	}

	public void beallitVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
		birtokos.torolPortal(this);
		birtokos = null;
		vegpont.hozzaadSzomszed(this);

		mukodesbeHelyezes(a);
	}

	public void beallitPar(Portal p) {
		Log.call();
		par = p;
	}

	private void mukodesbeHelyezes(Aszteroida a) {
		Log.call();
		if (Szkeleton.Kerdes("A portal párjának van végpontja?")) {
			beallitAktiv();
			par.beallitAktiv();
		}

	}

	private void Beszippant() {
		Log.call();
		if (Szkeleton.Kerdes("A portalnak van tulajdonosa?"))
			birtokos.torolPortal(this);
		if (Szkeleton.Kerdes("A portal rajta van egy aszteroidán?"))
			vegpont.torolSzomszed(this);

	}

	public void Utazas(Szereplo sz) {
		Log.call();
		if (Szkeleton.Kerdes("A portal aktív?"))
			par.Teleportalas(sz);
	}

	public void Teleportalas(Szereplo sz) {
		Log.call();
		vegpont.Utazas(sz);
	}

	public void setBirtokos(Telepes t) {
		Log.call();
		birtokos = t;
	}

	public void setVegpont(Aszteroida a) {
		Log.call();
		vegpont = a;
	}

}
