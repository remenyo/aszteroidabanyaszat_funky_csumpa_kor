package src;

public abstract class Hely {
	public abstract void Robbanas();
	public abstract void Utazas(Szereplo sz);
	public void szomszedRobbant(Aszteroida a) { //portal esetén nem kell megvalósítani
		Log.info("Meghivodott");
		Robbanas();
	}
}
