package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Szkeleton {

	public static boolean Kerdes(String kerdesSzoveg) {
		Scanner be = new Scanner(System.in);
		System.out.println(kerdesSzoveg + " 1=Igen 0=Nem\n");
		int valasz = be.nextInt();
		return valasz == 1;
	}

	public void Menu() {
		
		Scanner be = new Scanner(System.in);
		//while(true) {
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
			
			
			int valasz = be.nextInt();
			be = new Scanner(System.in);
			switch (valasz) {
				case 1: MozgasUrhajoval();break;
				case 2:	mozgasTeleporttal(); break;
				case 3: BanyaszatMenu();break;
				case 4: Vizjegfuras();break;
				case 5: telepesFurasUran();break;
				case 6: Vasfuras();break;
				case 7: portalkapuEpites();break;
				case 8: robotEpites();break;
				case 9: NyersanyagVisszahelyezesMenu();break;
				case 10: portalLehelyezes();break;
				case 11: robotFurasUran();break;
				case 12: Napvihar();break;
				case 13: System.exit(0);break;
					
		
				default:System.out.println("Nincs ilyen."); break;
			}
			
		//}
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
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Aszteroida b = new Aszteroida(1, true, new Nap(), new Szen());
		Uran u = new Uran();
		Telepes t = new Telepes();
		Telepes t2 = new Telepes();
		t2.setPortal(new Portal());
		t.hozzaadNyersanyag(new Szen());
		t.setPortal(new Portal());
		t2.hozzaadNyersanyag(new Szen());
		b.hozzaadSzereplo(t2);
		a.hozzaadSzereplo(t);
		a.hozzaadSzomszed(b);
		b.hozzaadSzomszed(a);
		t.beallitAszteroida(a);
		t2.beallitAszteroida(b);
		t.hozzaadNyersanyag(u);
		t.visszarakNyersanyag(u);
	}

	public void VizjegVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Vizjeg());
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
		p.setBirtokos(t);
		p2.setBirtokos(t);
		t.beallitAszteroida(a);
		a.hozzaadSzereplo(t);
		p.beallitPar(p2);
		p2.beallitPar(p);
		p.setVegpont(a);
		p2.setVegpont(b);
		
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
		t.beallitAszteroida(a);
		r.beallitAszteroida(a);
		a.hozzaadSzereplo(r);
		a.hozzaadSzereplo(t);
		nap.hozzaadAszteroidak(new ArrayList<Aszteroida>(Arrays.asList(a)));
		nap.Lepes();
	}
	
	public void portalkapuEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg();
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); //robotéptéshez használt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		//ArrayList<Nyersanyag> portalkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelelõ nyersanyagok feltöltése
		//portalkoltseg.forEach(nyersanyag -> nyk1.hozzaadNyersanyag(nyersanyag)); 

		
		t.epitPortal();
	}
	
	
	public void robotEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); //portalépítéshez használt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); 
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		t.beallitAszteroida(new Aszteroida(0, false, null, null));
		//ArrayList<Nyersanyag> robotkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelelõ nyersanyagok feltöltése
		//robotkoltseg.forEach(nyersanyag -> nyk2.hozzaadNyersanyag(nyersanyag)); 
		
		t.epitRobot();

	}
	
	public void nyersanyagVisszahelyezes() {
		//System.out.println("A telepesnél van nyersanyag?");
		
		//System.out.println("Az aszteroidában van már elhelyezve nyersanyag?");
		
		//System.out.println("Az aszteroida napközelben van?");
		
		//System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");
		
		//System.out.println("Maradtak életben játékosok?");

	}
	
	public void portalLehelyezes() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, new Nap(), new Szen()); //telepes helyezkedik el rajta
		Aszteroida a2 = new Aszteroida(3, false, new Nap(), new Szen()); //p2 lesz rajta
		t.beallitAszteroida(a);
		
		Portal p1 = new Portal(); //telepesé
		Portal p2 = new Portal(); //p1 szomszédja
		
		p1.setBirtokos(t);
		p2.setBirtokos(t);
		if(Kerdes("Van a telepesnél portal?")) {
			t.setPortal(p1);
			
			p1.beallitPar(p2);
			p2.beallitPar(p1);
			
			p2.setVegpont(a2);
			
			t.lehelyezPortal(p1);
		}
	}
}
