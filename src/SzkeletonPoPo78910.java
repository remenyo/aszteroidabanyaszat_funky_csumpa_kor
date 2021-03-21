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
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); //robot�pt�shez haszn�lt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		ArrayList<Nyersanyag> portalkoltseg = new ArrayList<Nyersanyag>(); //megfelel� nyersanyagok felt�lt�se
		portalkoltseg.forEach(nyersanyag -> nyk1.hozzaadNyersanyag(nyersanyag)); 

		
		boolean valasz = false;
		String hiba="";
		if(!Kerdes("A telepesn�l van m�r port�lkapu?")) {
			System.out.println("Nem siker�lt port�l l�trehozni: A telepesn�l van m�r port�lkapu");
		}
		else {
			if(!Kerdes("A telepesnek van elegend� nyersanyaga az �p�t�shez?")) {
				t.epitPortal();
				System.out.println("Nincs elegend� nyersanyag.");
			} else {
				portalkoltseg.forEach(nyersanyag -> t.hozzaadNyersanyag(nyersanyag)); 
				t.epitPortal();
				

				if(!Kerdes("Van elegend� nyersanyag a j�t�k befejez�s�hez?")) {
					Jatek.getInstance().jatekVegeVesztett();
				}
			} 	
		}
	}
	
	
	public void robotEpites() {
		Telepes t = new Telepes();
		//Jatek jatek = new Jatek();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); //portal�p�t�shez haszn�lt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); 
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		
		ArrayList<Nyersanyag> robotkoltseg = new ArrayList<Nyersanyag>(); //megfelel� nyersanyagok felt�lt�se
		robotkoltseg.forEach(nyersanyag -> nyk2.hozzaadNyersanyag(nyersanyag)); 
		
		
		if(!Kerdes("A telepesnek van elegend� nyersanyaga az �p�t�shez?")) {
			t.epitRobot();
			System.out.println("Nincs elegend� nyersanyag.");
		}
		else {
			robotkoltseg.forEach(nyersanyag -> t.hozzaadNyersanyag(nyersanyag)); 
			t.epitRobot();

			if(!Kerdes("Van elegend� nyersanyag a j�t�k befejez�s�hez?")) {
				Jatek.getInstance().jatekVegeVesztett();
			}
		}

	}
	
	public void nyersanyagVisszahelyez�s() {
		//System.out.println("A telepesn�l van nyersanyag?");
		
		//System.out.println("Az aszteroid�ban van m�r elhelyezve nyersanyag?");
		
		//System.out.println("Az aszteroida napk�zelben van?");
		
		//System.out.println("Van elegend� nyersanyag a j�t�k befejez�s�hez?");
		
		//System.out.println("Maradtak �letben j�t�kosok?");

	}
	
	public void portalLehelyezes() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, new Nap(), new Szen());
		t.beallitAszteroida(a);
		
		
		if(!Kerdes("A telepesn�l van Portal?")) {
			System.out.println("A telepesn�l nincs portal amit lehelyezhetne.");
		}
		else {
			t.setPortal(new Portal());
			
			if(!Kerdes("A port�l p�rj�nak van v�gpontja?")) {
				
			}
			
			if(!Kerdes("Van elegend� nyersanyag a j�t�k befejez�s�hez?")) {
				Jatek.getInstance().jatekVegeVesztett();
			}
		}
	}
}
