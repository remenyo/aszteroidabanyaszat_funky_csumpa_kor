package src;

// Mesterséges intelligencia, amely fúrhat vagy mozoghat
public class Robot extends Szereplo {

	Robot() {
		super(); // Beállításra kerülnek az alapértelmezések
		Log.ctor();
	}

	// Robbanáskor a robot árvándorol egy szomszédos aszteroidára
	public void Robbanas() {
		Log.call();
		mozgasIntelligencia();
	}

	// A robot lépése egy körben, ami lehet fúrás vagy mozgás
	public void Lepes() {
		Log.call();
		// TODO ez kell? int cselekves = RandomUtils.randomIntHatarokKozott(0, 4);
		// mekkora eséllyel fúrjon vagy mozogjon
		// (fúrás gyakrabb mint a mozgás)
		if (RandomUtils.randomBooleanValoszinuseggel(Jatek.ROBOT_MOZGAS_VALOSZINUSEG))
			mozgasIntelligencia();
		else
			Furas();
	}

	// A robot eldönti hogy hova szeretne mozogni
	public void mozgasIntelligencia() {
		Log.call();
		// Az aszteroida szomszédainak száma, a robot eldönti hogy hanyas számúra szeretne mozogni
		int szomszedszam = aszteroida.getSzomszedok().size() - 1;
		Integer sorszam = RandomUtils.randomIntHatarokKozott(0, szomszedszam);
		Mozgas(sorszam); // véletlenszerûen vándorlik egy szomszédra
	}

	/**
	 * Fúrja az aszteroidát, amin áll
	 */
	public void Furas() {
		Log.call();
		aszteroida.Furas();
	}

	public String toString() {
		return Szkeleton.getID(aszteroida) +":"+String.valueOf(lepett); /* + "" + (char) 13 + (char) 10 */
	}

	@Override
	public Boolean lepette() {
		return lepett;
	}
	@Override
	public void resetLepett() {
		lepett = false;
	}
}
