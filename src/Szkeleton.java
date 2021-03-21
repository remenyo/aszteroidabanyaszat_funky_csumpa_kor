package src;

import java.util.Scanner;

public class Szkeleton {
	
	public boolean Kerdes(String kerdesSzoveg) {
		Scanner be = new Scanner(System.in);
		System.out.println(kerdesSzoveg+" 1-Igen 0-Nem");
		int valasz = be.nextInt();
		return valasz==1;
	}
	
	public void Menu() {
		Scanner be = new Scanner(System.in);
		int valasz;
		System.out.println("------------------------");
		System.out.println("1. Mozgás ûrhajóval\r\n"
				+ "2. Mozgás teleport kapun keresztül\r\n"
				+ "3. Bányászat\r\n"
				+ "4. Vízjég Fúrás\r\n"
				+ "5. Urán fúrás\r\n"
				+ "6. Fúrás vas\r\n"
				+ "7. Portálkapu építés\r\n"
				+ "8. Robot építés\r\n"
				+ "9. Nyersanyag visszahelyezés\r\n"
				+ "10. Portál lehelyezés\r\n"
				+ "11. Robot Urán fúrás\r\n"
				+ "12. Napvihar");
		System.out.println("------------------------");
		valasz = be.nextInt();
		switch (valasz) {
			case 1: MozgasUrhajoval();break;
			case 2:break;
			case 3: BanyaszatMenu();break;
			case 4:break;
			case 5:break;
			case 6:break;
			case 7:break;
			case 8:break;
			case 9:break;
			case 10:break;
			case 11:break;
			case 12:break;
				
				
	
			default: break;
		}
	}
	
	public void BanyaszatMenu() {
		System.out.println("1. Urán Bányászat\r\n"
				+ "2. Vízjég Bányászat\r\n"
				+ "3. Szén Bányászat\r\n"
				+ "4. Vas Bányászat");
	}
	
	public void MozgasUrhajoval() {
		Telepes t = new Telepes();
		Aszteroida regi = new Aszteroida(1,true,new Nap(),new Uran());
		Aszteroida uj = new Aszteroida(1,true,new Nap(),new Uran());
		regi.hozzaadSzereplo(t);
		t.beallitAszteroida(regi);
		t.Mozgas();
	}
	
	public void NyersanyagVisszahelyezésMenu() {
		System.out.println("1. Urán Visszahelyzés\r\n"
				+ "2. Vízjég Bányászat\r\n"
				+ "3. Szén Bányászat\r\n"
				+ "4. Vas Bányászat");
	}
	
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
