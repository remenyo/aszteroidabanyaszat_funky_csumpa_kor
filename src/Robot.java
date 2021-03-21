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

		// mekkora eséllyel fúrjon vagy mozogjon
		// (fúrás gyakrabb mint a mozgás)
		if (cselekves % 4 == 0)
			mozgasIntelligencia();
		else
			Furas();

	}

	// Ne legyen kódismétlés
	// felhasználásra kerül mindkét függvényben
	public void mozgasIntelligencia() {
		Log.call();
		int szomszedszam = aszteroida.getSzomszedok().size();
		// Aszteroidába egy függvény, ami lekérdezi a szomszédok tömb hosszát;
		// szükséges az Aszteroida felrobbanásához, ha az aszteroidának nem maradtak
		// szomszédai
		// ~~Analízis model2 - 4.4.2 szekvencia

		Mozgas(0); // véletlenszerûen vándorlik egy szomszédra
	}

}
