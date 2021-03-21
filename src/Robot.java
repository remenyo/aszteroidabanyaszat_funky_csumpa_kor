package src;

import java.util.Random;

public class Robot {
	public void Robbanas() {
		mozgasIntelligencia();
	}
	public void Lepes() {
		Random rand = new Random();
		int cselekves = rand.nextInt(4);
		
		//mekkora eséllyel fúrjon vagy mozogjon
		//(fúrás gyakrabb mint a mozgás)
		if(cselekves%4==0)
			mozgasIntelligencia();
		else
			Furas();
		
	}
	
	//Ne legyen kódismétlés
	//felhasználásra kerül mindkét függvényben
	public void mozgasIntelligencia() {
		Random rand = new Random();
		int szomszedszam = aszteroida.getSzomszedSzam();
		//Aszteroidába egy függvény, ami lekérdezi a szomszédok tömb hosszát;
		//szükséges az Aszteroida felrobbanásához, ha az aszteroidának nem maradtak szomszédai
		//~~Analízis model2 - 4.4.2 szekvencia
		
		Mozgas( rand.nextInt(szomszedszam) ); //véletlenszerûen vándorlik egy szomszédra
	}
	
}
