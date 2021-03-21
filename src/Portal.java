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
		aktiv=true;
	}
	
	public Aszteroida getVegpont() {
		return vegpont;
	}
	public void beallitVegpont(Aszteroida a) {
		vegpont=a;
		birtokos.torolPortal(this);
		birtokos=null;
		vegpont.hozzaadSzomszed(this);
		
		mukodesbeHelyezes(a);
	}
	public void beallitPar(Portal p) {
		par=p;
	}
	private void mukodesbeHelyezes(Aszteroida a) {
     		
		if(Szkeleton.Kerdes("A portal párjának van végpontja?")) {
			beallitAktiv();
			par.beallitAktiv();
		}
			
	}
	private void Beszippant() {
		if(Szkeleton.Kerdes("A portalnak van tulajdonosa?"))
			birtokos.torolPortal(this);
		if(Szkeleton.Kerdes("A portal rajta van egy aszteroidán?"))
			vegpont.torolSzomszed(this);
			
	}
	public void Utazas(Szereplo sz) {
		if(Szkeleton.Kerdes("A portal párjának van végpontja?"))
			par.Teleportalas(sz);
	}
	public void Teleportalas(Szereplo sz) {
		vegpont.Utazas(sz);
	}
}
