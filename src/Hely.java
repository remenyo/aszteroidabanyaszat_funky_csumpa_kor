package src;

public abstract class Hely {
	public abstract void Robbanas();
	public abstract void Utazas(Szereplo sz);
	public void szomszedRobbant(Aszteroida a) { //portal eset�n nem kell megval�s�tani
		Log.info("Meghivodott");
		Robbanas();
	}
}
