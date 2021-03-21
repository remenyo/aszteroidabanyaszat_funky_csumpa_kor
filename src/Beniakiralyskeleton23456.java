package src;

public class Beniakiralyskeleton23456 {

	public void mozgasTeleporttal() 
	{
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(3,false,nap,new Szen());
		Portal p2 = new Portal();
		Aszteroida b = new Aszteroida(3,false,nap,new Szen());
		Portal p = new Portal();
		Telepes t = new Telepes();
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		p.beallitVegpont(a);
		p2.beallitVegpont(b);
		p.beallitPar(p2);
		p2.beallitPar(p);
		p.Utazas(t);	
	}
	
	public void uranBanyaszat() 
	{
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3,false,nap,new Uran());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
		
	}
	
	public void vasBanyaszat() 
	{
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3,false,nap,new Vas());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}
	
	public void vizjegBanyaszat() 
	{
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3,false,nap,new Vizjeg());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}
	
	public void szenBanyaszat() 
	{
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3,false,nap,new Szen());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}
	
}
