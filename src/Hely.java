package src;

//Az utaz�s az ezt az oszt�lyt megval�s�t�kon kereszt�l l�trej�het
public abstract class Hely {
	//Robban�s, melyet a lesz�rmazottak megval�s�tanak
	public abstract void Robbanas();

	//Szereplo utaztatása, melyet a lesz�rmazottak megval�s�tanak
	public abstract void Utazas(Szereplo sz);

	public void szomszedRobbant(Aszteroida a) { // portal eset�n nem kell megval�s�tani
		Robbanas(); // megh�vja a lesz�rmazottak robban�s�t
	}

	//Hely utaztatása, melyet a lesz�rmazottak megval�s�tanak
	public abstract void utazasHely(Hely hely);

	//Leszármazottak megvalósíthatják, nem csinál alapból semmit
	public void szomszedNapvihar(){

	}
}
