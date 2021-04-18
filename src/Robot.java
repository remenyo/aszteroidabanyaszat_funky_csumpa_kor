package src;

// Mesters�ges intelligencia, amely f�rhat vagy mozoghat
public class Robot extends Szereplo {

	Robot() {
		super(); // Be�ll�t�sra ker�lnek az alap�rtelmez�sek
		Log.ctor();
	}

	// Robban�skor a robot �rv�ndorol egy szomsz�dos aszteroid�ra
	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	// A robot l�p�se egy k�rben, ami lehet f�r�s vagy mozg�s
	public void Lepes() {
		Log.call();
		// TODO ez kell? int cselekves = RandomUtils.randomIntHatarokKozott(0, 4);
		// mekkora es�llyel f�rjon vagy mozogjon
		// (f�r�s gyakrabb mint a mozg�s)
		if (RandomUtils.randomBooleanValoszinuseggel(Jatek.ROBOT_MOZGAS_VALOSZINUSEG))
			mozgasIntelligencia();
		else
			Furas();
	}

	// A robot eld�nti hogy hova szeretne mozogni
	public void mozgasIntelligencia() {
		Log.call();
		// Az aszteroida szomsz�dainak sz�ma, a robot eld�nti hogy hanyas sz�m�ra szeretne
		// mozogni
		int szomszedszam = aszteroida.getSzomszedok().size() - 1;
		Integer sorszam = RandomUtils.randomIntHatarokKozott(0, szomszedszam);
		Mozgas(sorszam); // v�letlenszer�en v�ndorlik egy szomsz�dra
	}

	/**
	 * F�rja az aszteroid�t, amin �ll
	 */
	public void Furas() {
		Log.call();
		aszteroida.Furas();
	}

	public String toString() {
		return Szkeleton.getID(aszteroida) + ":"
				+ String.valueOf(lepett); /* + "" + (char) 13 + (char) 10 */
	}
}
