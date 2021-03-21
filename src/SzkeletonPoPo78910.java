package src;

import java.util.ArrayList;
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

		ArrayList<Nyersanyag> portalkoltseg = new ArrayList<Nyersanyag>(); //megfelelõ nyersanyagok feltöltése
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
				

				if(Kerdes("Van elegendõ nyersanyag a játék befejezéséhez?")) {
					Jatek.getInstance().jatekVegeNyert();
				}
			} 	
		}
	}
		
		

	
	
	public void robotEpites() {
		System.out.println("A telepesnek van elegendõ nyersanyaga az építéshez?");
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");

	}
	
	public void nyersanyagVisszahelyezés() {
		System.out.println("A telepesnél van nyersanyag?");
		
		System.out.println("Az aszteroidában van már elhelyezve nyersanyag?");
		
		System.out.println("Az aszteroida napközelben van?");
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");
		
		System.out.println("Maradtak életben játékosok?");

	}
	
	public void portalLehelyezes() {
		System.out.println("A telepesnél van Portal?");
		
		System.out.println("A portál párjának van végpontja?"); //bõvült
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");


	}
}
