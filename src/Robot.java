package src;

import java.util.Random;

public class Robot {
	public void Robbanas() {
		mozgasIntelligencia();
	}
	public void Lepes() {
		Random rand = new Random();
		int cselekves = rand.nextInt(4);
		
		//mekkora es�llyel f�rjon vagy mozogjon
		//(f�r�s gyakrabb mint a mozg�s)
		if(cselekves%4==0)
			mozgasIntelligencia();
		else
			Furas();
		
	}
	
	//Ne legyen k�dism�tl�s
	//felhaszn�l�sra ker�l mindk�t f�ggv�nyben
	public void mozgasIntelligencia() {
		Random rand = new Random();
		int szomszedszam = aszteroida.getSzomszedSzam();
		//Aszteroid�ba egy f�ggv�ny, ami lek�rdezi a szomsz�dok t�mb hossz�t;
		//sz�ks�ges az Aszteroida felrobban�s�hoz, ha az aszteroid�nak nem maradtak szomsz�dai
		//~~Anal�zis model2 - 4.4.2 szekvencia
		
		Mozgas( rand.nextInt(szomszedszam) ); //v�letlenszer�en v�ndorlik egy szomsz�dra
	}
	
}
