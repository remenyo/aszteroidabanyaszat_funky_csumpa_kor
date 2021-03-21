package src;

public class Beniakiralyskeleton23456 {

	public void robotFurasUran() 
	{
		Robot r = new Robot();
		Aszteroida a = new Aszteroida(2,false,new Nap(),new Uran());
		Aszteroida b = new Aszteroida(2,false,new Nap(),new Uran());
		Portal p = new Portal();
		a.hozzaadSzomszed(b);
		a.hozzaadSzomszed(p);
		a.Furas();
	
			
	}
	
	
	
}
