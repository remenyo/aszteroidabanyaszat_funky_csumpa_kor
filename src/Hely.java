package src;

// Az utazďż˝s az ezt az osztďż˝lyt megvalďż˝sďż˝tďż˝kon keresztďż˝l lďż˝trejďż˝het
public abstract class Hely {
	// Robbanďż˝s, melyet a leszďż˝rmazottak megvalďż˝sďż˝tanak
	public abstract void Robbanas();

	// Szereplo utaztatĂˇsa, melyet a leszďż˝rmazottak megvalďż˝sďż˝tanak
	public abstract void Utazas(Szereplo sz);

	public void szomszedRobbant(Aszteroida a) { // portal esetďż˝n nem kell megvalďż˝sďż˝tani
		Robbanas(); // meghďż˝vja a leszďż˝rmazottak robbanďż˝sďż˝t
	}

	// Hely utaztatĂˇsa, melyet a leszďż˝rmazottak megvalďż˝sďż˝tanak
	public abstract void utazasHely(Hely hely);

	// LeszĂˇrmazottak megvalĂłsĂ­thatjĂˇk, nem csinĂˇl alapbĂłl semmit
	public void szomszedNapvihar() {

	}
}
