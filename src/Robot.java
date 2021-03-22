package src;

//Mesterséges intelligencia, amely fúrhat vagy mozoghat
public class Robot extends Szereplo {

	Robot() {
		super(); //Beállításra kerülnek az alapértelmezések
		Log.ctor();
	}

	//Robbanáskor a robot árvándorol egy szomszédos aszteroidára
	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	//A robot lépése egy körben, ami lehet fúrás vagy mozgás
	public void Lepes() {
		Log.call();
		int cselekves = RandomUtils.randomIntHatarokKozott(0, 4);

		// mekkora eséllyel fúrjon vagy mozogjon
		// (fúrás gyakrabb mint a mozgás)
		if (cselekves % 4 == 0)
			mozgasIntelligencia();
		else
			Furas();

	}

	// A robot eldönti hogy hova szeretne mozogni
	public void mozgasIntelligencia() {
		Log.call();
		//Az aszteroida szomszédainak száma, a robot eldönti hogy hanyas számúra szeretne mozogni
		int szomszedszam = aszteroida.getSzomszedok().size();

		Mozgas(0); // véletlenszerûen vándorlik egy szomszédra
	}

}
