package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BrahlazsSzkelton {
	
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
		System.out.println("1. Mozg�s �rhaj�val\r\n"
				+ "2. Mozg�s teleport kapun kereszt�l\r\n"
				+ "3. B�ny�szat\r\n"
				+ "4. V�zj�g F�r�s\r\n"
				+ "5. Ur�n f�r�s\r\n"
				+ "6. F�r�s vas\r\n"
				+ "7. Port�lkapu �p�t�s\r\n"
				+ "8. Robot �p�t�s\r\n"
				+ "9. Nyersanyag visszahelyez�s\r\n"
				+ "10. Port�l lehelyez�s\r\n"
				+ "11. Robot Ur�n f�r�s\r\n"
				+ "12. Napvihar");
		System.out.println("------------------------");
		valasz = be.nextInt();
		switch (valasz) {
			case 1: MozgasUrhajoval();break;
			case 2:	Vizjegfuras(); break;
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
		System.out.println("1. Ur�n B�ny�szat\r\n"
				+ "2. V�zj�g B�ny�szat\r\n"
				+ "3. Sz�n B�ny�szat\r\n"
				+ "4. Vas B�ny�szat");
	}
	
	public void MozgasUrhajoval() {
		Telepes t = new Telepes();
		Aszteroida regi = new Aszteroida(1,true,new Nap(),new Uran());
		Aszteroida uj = new Aszteroida(1,true,new Nap(),new Uran());
		regi.hozzaadSzereplo(t);
		t.beallitAszteroida(regi);
		t.Mozgas();
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
		nap.Napvihar();
	}
	
}
