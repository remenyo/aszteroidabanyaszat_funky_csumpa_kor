package src;


public class Portal extends Hely {
	private Boolean aktiv=false;
	private Telepes birtokos;
	private Portal par;
	private Aszteroida vegpont;
	
	public void Robbanas() {
		par.Beszippant();
		Beszippant();
	}
	
	void beallitAktiv() {
		Log.info("Meghivodott");
		aktiv=true;
	}
	
	public Aszteroida getVegpont() {
		Log.info("Meghivodott");
		return vegpont;
	}
	public void beallitVegpont(Aszteroida a) {
		Log.info("Meghivodott");
		vegpont=a;
		birtokos.torolPortal(this);
		birtokos=null;
		vegpont.hozzaadSzomszed(this);
		
		mukodesbeHelyezes(a);
	}
	public void beallitPar(Portal p) {
		Log.info("Meghivodott");
		par=p;
	}
	private void mukodesbeHelyezes(Aszteroida a) {
		Log.info("Meghivodott");
		if(Szkeleton.Kerdes("A portal párjának van végpontja?")) {
			beallitAktiv();
			par.beallitAktiv();
		}
			
	}
	private void Beszippant() {
		Log.info("Meghivodott");
		if(Szkeleton.Kerdes("A portalnak van tulajdonosa?"))
			birtokos.torolPortal(this);
		if(Szkeleton.Kerdes("A portal rajta van egy aszteroidán?"))
			vegpont.torolSzomszed(this);
			
	}
	public void Utazas(Szereplo sz) {
		Log.info("Meghivodott");
		if(Szkeleton.Kerdes("A portal aktív?"))
			par.Teleportalas(sz);
	}
	public void Teleportalas(Szereplo sz) {
		Log.info("Meghivodott");
		vegpont.Utazas(sz);
	}
	public void setBirtokos(Telepes t) {
		Log.info("Meghivodott");
		birtokos = t;
	}
	
}
