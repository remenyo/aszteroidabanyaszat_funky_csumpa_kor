package src;

//Az utazás az ezt az osztályt megvalósítókon keresztül létrejöhet
public abstract class Hely {
	//Robbanás, melyet a leszármazottak megvalósítanak
	public abstract void Robbanas();

	//Utazás, melyet a leszármazottak megvalósítanak
	public abstract void Utazas(Szereplo sz);

	public void szomszedRobbant(Aszteroida a) { // portal esetén nem kell megvalósítani
		Log.call();
		Robbanas(); // meghívja a leszármazottak robbanását
	}
}
