package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Szkeleton {

	public static boolean Kerdes(String kerdesSzoveg) {
		Scanner be = new Scanner(System.in);
		System.out.println(kerdesSzoveg + " 1=Igen 0=Nem\n");
		int valasz = be.nextInt();
		be.close();
		return valasz == 1;
	}

	public void Menu() {
		
		int valasz;
		while(true) {
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
			Scanner be = new Scanner(System.in);
			valasz = be.nextInt();
			switch (valasz) {
				case 1: MozgasUrhajoval();break;
				case 2:	Vizjegfuras(); break;
				case 3: BanyaszatMenu();break;
				case 4: Vizjegfuras();break;
				case 5: telepesFurasUran();
				case 6: Vasfuras();break;
				case 7: /*"popo"*/;break;
				case 8:/*"popo"*/;break;
				case 9: NyersanyagVisszahelyezesMenu();break;
				case 10: /*"popo"*/break;
				case 11: robotFurasUran();
				case 12: Napvihar();
				case 13: System.exit(0);	
					
		
				default:System.out.println("Nincs ilyen."); break;
			}
		}
	}

	public void BanyaszatMenu() {
		System.out.println("1. Urán Bányászat\r\n"
				+ "2. Vízjég Bányászat\r\n"
				+ "3. Szén Bányászat\r\n"
				+ "4. Vas Bányászat");
		Scanner be = new Scanner(System.in);
		int valasz = be.nextInt();
		switch (valasz) {
			case 1: uranBanyaszat();break;
			case 2:	vizjegBanyaszat(); break;
			case 3: szenBanyaszat();break;
			case 4: vasBanyaszat();break;
				
			default: break;
		}
	}

	public void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Urán visszahelyezés\r\n"
				+ "2. Vízjég visszahelyezés\r\n"
				+ "3. Szén visszahelyezés\r\n"
				+ "4. Vas visszahelyezés");
		Scanner be = new Scanner(System.in);
		int valasz = be.nextInt();
		switch (valasz) {
			case 1: UranVisszahelyezes();break;
			case 2:	VizjegVisszahelyezes(); break;
			case 3: break;
			case 4: break;
				
			default: break;
		}
	}

	public void MozgasUrhajoval() {
		Telepes t = new Telepes();
		Aszteroida regi = new Aszteroida(1, true, new Nap(), new Uran());
		Aszteroida uj = new Aszteroida(1, true, new Nap(), new Uran());
		regi.hozzaadSzomszed(uj);
		uj.hozzaadSzomszed(regi);
		regi.hozzaadSzereplo(t);
		t.beallitAszteroida(regi);
		t.Mozgas(0);
	}

	public void UranVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Uran());
		Uran u = new Uran();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(u);
		t.visszarakNyersanyag(u);
	}

	public void VizjegVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Uran());
		Vizjeg v = new Vizjeg();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(v);
		t.visszarakNyersanyag(v);
	}

	public void mozgasTeleporttal() {
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(3, false, nap, new Szen());
		Portal p2 = new Portal();
		Aszteroida b = new Aszteroida(3, false, nap, new Szen());
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

	public void uranBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Uran());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public void vasBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Vas());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public void vizjegBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Vizjeg());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}

	public void szenBanyaszat() {
		Nap nap = new Nap();
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, nap, new Szen());
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		a.Banyaszat();
	}
	public void robotFurasUran() {
		Robot r = new Robot();
		Aszteroida a = new Aszteroida(2,false,new Nap(),new Uran());
		Aszteroida b = new Aszteroida(2,false,new Nap(),new Uran());
		Portal p = new Portal();
		a.hozzaadSzomszed(b);
		a.hozzaadSzomszed(p);
		a.Furas();
	
			
	}
	
	public void telepesFurasUran() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(2,false,new Nap(),new Uran());
		Aszteroida b = new Aszteroida(2,false,new Nap(),new Uran());
		Portal p = new Portal();
		a.hozzaadSzomszed(b);
		a.hozzaadSzomszed(p);
		a.Furas();
	}
	
	public void Vizjegfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1,true,new Nap(),new Vizjeg());
		t.beallitAszteroida(a);
		t.Furas();
	}
	
	public void Vasfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1,true,new Nap(),new Vas());
		t.beallitAszteroida(a);
		t.Furas();
	}
	
	public void Napvihar() {
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(1,true,nap,new Vas());
		Telepes t = new Telepes();
		Robot r = new Robot();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		r.beallitAszteroida(a);
		a.hozzaadSzereplo(r);
		nap.hozzaadAszteroidak(new ArrayList<Aszteroida>(Arrays.asList(a)));
		nap.Lepes();
	}
}
