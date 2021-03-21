package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SzkeletonPoPo78910 {
	public boolean Kerdes(String kerdesSzoveg) {
		Scanner be = new Scanner(System.in);
		System.out.println(kerdesSzoveg+" 1-Igen 0-Nem");
		int valasz = be.nextInt();
		return valasz==1;
	}
	
	public void portalkapuEpites() {
		Telepes t = new Telepes();
		//Jatek jatek = new Jatek();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg();
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); //robotéptéshez használt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		ArrayList<Nyersanyag> portalkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelelõ nyersanyagok feltöltése
		portalkoltseg.forEach(nyersanyag -> nyk1.hozzaadNyersanyag(nyersanyag)); 

		
		boolean valasz = false;
		String hiba="";
		if(!Kerdes("A telepesnél van már portálkapu?")) {
			System.out.println("Nem sikerült portál létrehozni: A telepesnél van már portálkapu");
		}
		else {
			if(!Kerdes("A telepesnek van elegendõ nyersanyaga az építéshez?")) {
				t.epitPortal();
				System.out.println("Nincs elegendõ nyersanyag.");
			} else {
				portalkoltseg.forEach(nyersanyag -> t.hozzaadNyersanyag(nyersanyag)); 
				t.epitPortal();
				

				if(!Kerdes("Van elegendõ nyersanyag a játék befejezéséhez?")) {
					Jatek.getInstance().jatekVegeVesztett();
					System.out.println("A játéknak vége, a játékosok vesztettek");
				}
			} 	
		}
	}
	
	
	public void robotEpites() {
		Telepes t = new Telepes();
		//Jatek jatek = new Jatek();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); //portalépítéshez használt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); 
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		
		ArrayList<Nyersanyag> robotkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelelõ nyersanyagok feltöltése
		robotkoltseg.forEach(nyersanyag -> nyk2.hozzaadNyersanyag(nyersanyag)); 
		
		
		if(!Kerdes("A telepesnek van elegendõ nyersanyaga az építéshez?")) {
			t.epitRobot();
			System.out.println("Nincs elegendõ nyersanyag.");
		}
		else {
			robotkoltseg.forEach(nyersanyag -> t.hozzaadNyersanyag(nyersanyag)); 
			t.epitRobot();

			if(!Kerdes("Van elegendõ nyersanyag a játék befejezéséhez?")) {
				Jatek.getInstance().jatekVegeVesztett();
				System.out.println("A játéknak vége, a játékosok vesztettek");
			}
		}

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
		Portal p2 = new Potral(); //p1 szomszédja
		
		p1.beallitPar(p2);
		p2.beallitPar(p1);
		
		
		if(!Kerdes("A telepesnél van Portal?")) {
			System.out.println("A telepesnél nincs portal amit lehelyezhetne.");
		}
		else {			
			t.setPortal(p1);
			
			if(!Kerdes("A portál párjának van végpontja?")) {
				t.lehelyezPortal(p1);
				
				System.out.println("A portal lehelyezve, viszont nem aktív");
			}
			else {
				p2.beallitVegpont(a2);
				
				t.lehelyezPortal(p1);
				
				System.out.println("A portal lehelyezve, és aktív");
			}
			
			if(!Kerdes("Van elegendõ nyersanyag a játék befejezéséhez?")) {
				Jatek.getInstance().jatekVegeVesztett();
				System.out.println("A játéknak vége, a játékosok vesztettek");
			}
		}
	}
}
