package src;

// Mesterséges intelligencia, amely fúrhat vagy mozoghat
public class Robot extends Szereplo {

	/**
	 * Robot konstora
	 */
	Robot() {
		super(); // Beállításra kerülnek az alapértelmezések
		Log.ctor();
	}

	/**
	 * Robbanáskor a robot átvándorol egy szomszédos aszteroidára
	 */
	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	/**
	 * A robot lépése egy körben, ami lehet fúrás vagy mozgás
	 */
	public void Lepes() {
		Log.call();
		if (RandomUtils.randomBooleanValoszinuseggel(Jatek.ROBOT_MOZGAS_VALOSZINUSEG))
			mozgasIntelligencia();
		else
			Furas();
	}

	/**
	 * A robot eldönti hogy hova szeretne mozogni
	 */
	public void mozgasIntelligencia() {
		Log.call();
		// Az aszteroida szomszédainak száma, a robot eldönti hogy hanyas számúra szeretne
		// mozogni
		int szomszedszam = aszteroida.getSzomszedok().size() - 1;
		Integer sorszam = RandomUtils.randomIntHatarokKozott(0, szomszedszam);
		Mozgas(sorszam); // véletlenszerűen vándorlik egy szomszédra
	}

	/**
	 * Fúrja az aszteroidát, amin áll
	 */
	public void Furas() {
		Log.call();
		aszteroida.Furas();
	}

	/**
	 * Visszaadja az adatait a telepesnek.
	 * 
	 * @return Robot adatai
	 */
	public String toString() {
		return Szkeleton.getID(aszteroida) + ":" + String.valueOf(lepett);
	}

	/**
	 * Mozgatja a robotot a megadott sorszámú szomszédra
	 */
	public void Mozgas(Integer sorszam) {
		Log.call();
		if (Jatek.robot_robbanas_elso_szomszed) {
			super.Mozgas(0);
		} else {
			super.Mozgas(sorszam);
		}

	}
}
