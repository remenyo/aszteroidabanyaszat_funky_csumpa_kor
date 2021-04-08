package src;

//Mesters�ges intelligencia, amely f�rhat vagy mozoghat
public class Robot extends Szereplo {

	Robot() {
		super(); //Be�ll�t�sra ker�lnek az alap�rtelmez�sek
		Log.ctor();
	}

	//Robban�skor a robot �rv�ndorol egy szomsz�dos aszteroid�ra
	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	//A robot l�p�se egy k�rben, ami lehet f�r�s vagy mozg�s
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

	// A robot eld�nti hogy hova szeretne mozogni
	public void mozgasIntelligencia() {
		Log.call();
		//Az aszteroida szomsz�dainak sz�ma, a robot eld�nti hogy hanyas sz�m�ra szeretne mozogni
		int szomszedszam = aszteroida.getSzomszedok().size();

		Mozgas(0); // v�letlenszer�en v�ndorlik egy szomsz�dra
	}

}
