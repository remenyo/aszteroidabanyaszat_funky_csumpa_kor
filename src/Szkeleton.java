package src;

import java.util.ArrayList;
import java.util.Arrays;

public class Szkeleton {


	/**
	 * Feltesz egy kérdést
	 * 
	 * @param kerdes kérdés szövege
	 * @return boolean válasz
	 */
	public static boolean Kerdes(String kerdes) {
		return Cin.getBool(kerdes);
	}

	public void Menu() {

		switch (Cin.kerdez_tobbvalasz("MENÜ", "Mozgás ûrhajóval", "Mozgás teleport kapun keresztül",
				"Bányászat", "Vízjég Fúrás", "Urán fúrás", "Fúrás vas", "Portálkapu építés",
				"Robot építés", "Nyersanyag visszahelyezés", "Portál lehelyezés",
				"Robot Urán fúrás", "Napvihar")) {
			case 1:
				MozgasUrhajoval();
				break;
			case 2:
				mozgasTeleporttal();
				break;
			case 3:
				BanyaszatMenu();
				break;
			case 4:
				Vizjegfuras();
				break;
			case 5:
				telepesFurasUran();
				break;
			case 6:
				Vasfuras();
				break;
			case 7:
				portalkapuEpites();
				break;
			case 8:
				robotEpites();
				break;
			case 9:
				NyersanyagVisszahelyezesMenu();
				break;
			case 10:
				portalLehelyezes();
				break;
			case 11:
				robotFurasUran();
				break;
			case 12:
				Napvihar();
				break;
			case 13:
				System.exit(0);
				break;

			default:
				break;
		}


	}

	public void BanyaszatMenu() {
		switch (Cin.kerdez_tobbvalasz("BÁNYÁSZAT", "Urán Bányászat", "Vízjég Bányászat",
				"Szén Bányászat", "Vas Bányászat")) {
			case 1:
				uranBanyaszat();
				break;
			case 2:
				vizjegBanyaszat();
				break;
			case 3:
				szenBanyaszat();
				break;
			case 4:
				vasBanyaszat();
				break;

			default:
				break;
		}
	}

	public void NyersanyagVisszahelyezesMenu() {
		System.out.println("1. Urán visszahelyezés\r\n" + "2. Vízjég visszahelyezés\r\n"
				+ "3. Szén visszahelyezés\r\n" + "4. Vas visszahelyezés");
		switch (Cin.kerdez_tobbvalasz("BÁNYÁSZAT", "Urán visszahelyezés", "Vízjég visszahelyezés",
				"Szén visszahelyezés", "Vas visszahelyezés")) {
			case 1:
				UranVisszahelyezes();
				break;
			case 2:
				VizjegVisszahelyezes();
				break;
			case 3:
				VasVisszahelyez();
				break;
			case 4:
				SzenVisszahelyez();
				break;

			default:
				break;
		}
	}

	// Az elején levõ inicializálás mindenhol a megfelelõ mûködés érdekében van.
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

	public void VasVisszahelyez() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Vas v = new Vas();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(v);
		t.visszarakNyersanyag(v);
	}

	public void SzenVisszahelyez() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Szen sz = new Szen();
		Telepes t = new Telepes();
		a.hozzaadSzereplo(t);
		t.beallitAszteroida(a);
		t.hozzaadNyersanyag(sz);
		t.visszarakNyersanyag(sz);
	}

	public void UranVisszahelyezes() {
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
		Aszteroida b = new Aszteroida(1, true, new Nap(), new Vas());
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
		Aszteroida a = new Aszteroida(1, true, new Nap(), null);
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
		a.hozzaadSzomszed(p);
		t.Mozgas(0); // Ha nem mozgunk akkor is megkérdezi megvan-e az összes nyersanyag.
						// Rendes játékban ez egy automatikus ellenõrzés lesz ami nem ront el semmit
						// és nem fogyaszt sok erõforrást így fejlesztõi döntés miatt marad.
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
		Robot r2 = new Robot();
		Aszteroida a = new Aszteroida(2, false, new Nap(), new Uran());
		Aszteroida b = new Aszteroida(2, false, new Nap(), new Vas());

		b.hozzaadSzereplo(r2);
		a.hozzaadSzereplo(r);
		a.hozzaadSzomszed(b);
		b.hozzaadSzomszed(a);
		r.beallitAszteroida(a);
		r2.beallitAszteroida(b);
		a.Furas();

	}

	public void telepesFurasUran() {

		Aszteroida a = new Aszteroida(1, true, new Nap(), new Uran());
		Aszteroida b = new Aszteroida(1, true, new Nap(), new Szen());
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

		a.Furas();
	}

	public void Vizjegfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Vizjeg());
		t.beallitAszteroida(a);
		t.Furas();
	}

	public void Vasfuras() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(1, true, new Nap(), new Vas());
		t.beallitAszteroida(a);
		t.Furas();
	}

	public void Napvihar() {
		Nap nap = new Nap();
		Aszteroida a = new Aszteroida(1, true, nap, new Vas());
		Telepes t = new Telepes();
		Robot r = new Robot();
		t.hozzaadNyersanyag(new Vas());
		t.setPortal(new Portal());
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
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg(); // robotéptéshez használt
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);

		t.epitPortal();
	}

	public void robotEpites() {
		Telepes t = new Telepes();
		NyersanyagKoltseg nyk1 = new NyersanyagKoltseg(); // portalépítéshez használt
		NyersanyagKoltseg nyk2 = new NyersanyagKoltseg();
		t.hozzaadKoltseg(nyk1);
		t.hozzaadKoltseg(nyk2);
		t.beallitAszteroida(new Aszteroida(0, false, null, null));

		t.epitRobot();

	}

	public void portalLehelyezes() {
		Telepes t = new Telepes();
		Aszteroida a = new Aszteroida(3, false, new Nap(), new Szen()); // telepes helyezkedik el
																		// rajta
		Aszteroida a2 = new Aszteroida(3, false, new Nap(), new Szen()); // p2 lesz rajta
		t.beallitAszteroida(a);

		Portal p1 = new Portal(); // telepesé
		Portal p2 = new Portal(); // p1 szomszédja

		p1.setBirtokos(t);
		p2.setBirtokos(t);
		if (Kerdes("Van a telepesnél portal?")) {
			t.setPortal(p1);

			p1.beallitPar(p2);
			p2.beallitPar(p1);

			p2.setVegpont(a2);

			t.lehelyezPortal(p1);
		}
	}
}
