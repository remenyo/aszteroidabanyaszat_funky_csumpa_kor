package src;

import java.util.Scanner;

public class Portal {
	private Boolean aktiv=false;
	private Telepes birtoskos;
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
		Scanner in = new Scanner(System.in);
        System.out.println("Meg van nála az összes szükséges nyersanyag?\n 1:Igen\n 2:Nem");
        int megvan = in.nextInt();
		
		if(par.getVegpont()!=null) {
			beallitAktiv();
			par.beallitAktiv();
		}
			
	}
	private void Beszippant() {
		if(birtokos!=null)
			birtokos.torolPortal(this);
		if(vegpont!=null)
			vegpont.torolSzomszed(this);
			
	}
	public void Utazas(Szereplo sz) {
		if(par!=null)
			par.Teleportalas(sz);
	}
	public void Teleportalas(Szereplo sz) {
		vegpont.Utazas(sz);
	}
}
