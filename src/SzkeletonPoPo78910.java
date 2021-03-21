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
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg();
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); //robot�pt�shez haszn�lt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		//ArrayList<Nyersanyag> portalkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelel� nyersanyagok felt�lt�se
		//portalkoltseg.forEach(nyersanyag -> nyk1.hozzaadNyersanyag(nyersanyag)); 

		
		t.epitPortal();
	}
	
	
	public void robotEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); //portal�p�t�shez haszn�lt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); 
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		
		//ArrayList<Nyersanyag> robotkoltseg = new ArrayList<Nyersanyag>(Arrays.asList(new Szen(), new Vas())); //megfelel� nyersanyagok felt�lt�se
		//robotkoltseg.forEach(nyersanyag -> nyk2.hozzaadNyersanyag(nyersanyag)); 
		
		t.epitRobot();

	}
	
	public void nyersanyagVisszahelyezes() {
		//System.out.println("A telepesn�l van nyersanyag?");
		
		//System.out.println("Az aszteroid�ban van m�r elhelyezve nyersanyag?");
		
		//System.out.println("Az aszteroida napk�zelben van?");
		
		//System.out.println("Van elegend� nyersanyag a j�t�k befejez�s�hez?");
		
		//System.out.println("Maradtak �letben j�t�kosok?");

	}
	
	public void portalLehelyezes() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, new Nap(), new Szen()); //telepes helyezkedik el rajta
		Aszteroida a2 = new Aszteroida(3, false, new Nap(), new Szen()); //p2 lesz rajta
		t.beallitAszteroida(a);
		
		Portal p1 = new Portal(); //telepes�
		Portal p2 = new Portal(); //p1 szomsz�dja
		
		if(Kerdes("Van a telepesn�l portal?")) {
			t.setPortal(p1);
			
			p1.beallitPar(p2);
			p2.beallitPar(p1);
			
			p2.beallitVegpont(a2);
			
			t.lehelyezPortal(p1);
		}
	}
}
