package src;

public class Robot extends Szereplo {

	Robot() {
		super();
		Log.ctor();
	}

	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	public void Lepes() {
		Log.call();
		int cselekves = RandomUtils.randomIntHatarokKozott(0, 4);

		// mekkora es�llyel f�rjon vagy mozogjon
		// (f�r�s gyakrabb mint a mozg�s)
		if (cselekves % 4 == 0)
			mozgasIntelligencia();
		else
			Furas();

	}

	// Ne legyen k�dism�tl�s
	// felhaszn�l�sra ker�l mindk�t f�ggv�nyben
	public void mozgasIntelligencia() {
		Log.call();
		int szomszedszam = aszteroida.getSzomszedok().size();
		// Aszteroid�ba egy f�ggv�ny, ami lek�rdezi a szomsz�dok t�mb hossz�t;
		// sz�ks�ges az Aszteroida felrobban�s�hoz, ha az aszteroid�nak nem maradtak
		// szomsz�dai
		// ~~Anal�zis model2 - 4.4.2 szekvencia

		Mozgas(0); // v�letlenszer�en v�ndorlik egy szomsz�dra
	}

}
