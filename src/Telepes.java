package src;

import java.util.ArrayList;

// Telepes a Szereplõ leszármazottja, ismeri a nála lévõ portálokat, nyersanyagokat és
// a statikus épí­tési költségek is ebbe az osztályba találhatók.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok; // A telepesnél lévõ nyersanyagokat tárolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>(); // A
																									// portál
																									// és
																									// robot
																									// épí­tésének
																									// a
	// költségét tárolja.
	private ArrayList<Portal> portal;// A Telepesnél lévõ portálokat tárolja.

	Telepes() {
		nyersanyagok = new ArrayList<Nyersanyag>();
		portal = new ArrayList<Portal>();
		Jatek.telepesszam++;
	}

	/**
	 * Meghí­vja a szereplõ mozgás függvényét amivel egy mésik aszteroidára utazik a telepes, majd
	 * meghí­vja azon az aszteroidán az ellenõrizNyert fv-t hogy ellenõrizzük összegyült-e elég
	 * nyersanyag a játék megnyeréséhez.
	 * 
	 * @param sorszam Az aszteroida azonosí­tója
	 */
	public void Mozgas(int sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	/**
	 * A telepes meghí­vja a Banyaszat() fv-t azon az aszteroidán amelyiken áll, ha van nyersanyag
	 * az aszteroidába akkor azt magához veszi és meghí­vja az ellenõriznyertet az aszteroidán.
	 */
	public void Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (temp != null) {
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		}
	}


	/**
	 * Visszaadja a telepesnél lévõ nyersanyagok listáját.
	 * 
	 * @return ArrayList<Nyersanyag> A telepesnél lévõ nyersanyagok listája.
	 */
	@Override
	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.call();
		return nyersanyagok;
	}


	private void mozgasMenu() {
		Integer szomszeddb = aszteroida.getSzomszedok().size() + 1;
		Integer valasztas = Cin.getInt("Melyik helyre szeretne utazni az aszteroida " + szomszeddb
				+ " db szomszédja közül? (1-" + szomszeddb);
		if ((valasztas > szomszeddb) && (valasztas <= 0)) {
			System.out.println("Nem jót adott meg!");
		} else {
			Mozgas(valasztas - 1);
		}
	}

	private void nyersanyagvisszarakMenu() {
		if (!nyersanyagok.isEmpty()) {
			System.out
					.println("Melyik nyersanyagot szeretné visszarakni?(sorszámával válaszoljon)");
			Integer db = 0;
			for (int i = 0; i < nyersanyagok.size(); i++) {
				if (nyersanyagok.get(i).getNev().equals("Urán")) {
					System.out.println(i + 1 + ". " + nyersanyagok.get(i).getNev()
							+ " napközelben felszí­nre kerülések száma: "
							+ ((Uran) nyersanyagok.get(i)).getnapFenyerte());
				} else {
					System.out.print(i + 1 + ". " + nyersanyagok.get(i).getNev());
				}
				db = i + 1;
			}
			Integer valasztas = Cin.getInt();
			if ((valasztas > db) && (valasztas <= 0)) {
				System.out.println("Nem jót adott meg!");
			} else {
				visszarakNyersanyag(nyersanyagok.get(valasztas - 1));
			}
		} else {
			System.out.println("Nincs nyersanyaga!");
		}
	}


	/**
	 * Teszt alatt egyszer se hí­vódik meg de majd rendes mûködés alatt itt választhat a
	 * felhasználó, hogy mit szeretne csinálni a telepessel, és a választásnak megfelelõ függvény
	 * fog meghí­vódni.
	 */
	@Override
	public void Lepes() {
		Log.call();
		Boolean elorejelzes = aszteroida.getElorejelzesvan();
		if (elorejelzes) {
			Log.userInfo("Kovetkezo korbe napvihar lesz");
		}
		int valasz = Cin.kerdez_tobbvalasz("Képességek", "Mozgás", "Fúrás", "Bányászat",
				"Robotépí­tés és lehelyezés", "Teleportkapupár-épí­tés", "Teleportkapu-lehelyezés",
				"Nyersanyag visszahelyezés");
		switch (valasz) {
			case 1:
				mozgasMenu();
				break;
			case 2:
				Furas();
				break;
			case 3:
				Banyaszat();
				break;
			case 4:
				epitRobot();
				break;
			case 5:
				epitPortal();
				break;
			case 6:
				if (!portal.isEmpty()) {
					lehelyezPortal(portal.get(0));
				}
				break;
			case 7:
				nyersanyagvisszarakMenu();
				return;
			default:
				break;
		}
		// Scanner sc = new Scanner(System.in);
		// TODO 7 opciobol választás bemenet alapján
	}

	/**
	 * A Telepes átasdja a nyersanyagait az épí­tésköltség-nek ami megmondja, hogy van e elég a
	 * robot épí­téséhez, ha van elég akkor épí­t egy robotot és rá utaztatja az aszteroidára amin
	 * áll éppen, ha nincs elég nyersanyag akkor nem csinál semmmit.
	 */
	public Robot epitRobot() {
		Log.call();
		Boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok);
		if (vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
			return r;
		}
		return null;
	}


	/**
	 * Ha a Telepesnél nincs már portál, átasdja a nyersanyagait az épí­tésköltség-nek ami
	 * megmondja, hogy van e elég a portál épí­téséhez, ha van elég akkor épí­t egy portál párt és
	 * rá és elrakja magának, ha nincs elég nyersanyag akkor nem csinál semmmit, ha van néla portál
	 * akkor se csinál semmit.
	 */
	public ArrayList<Portal> epitPortal() {
		Log.call();
		Portal p1;
		Portal p2;
		ArrayList<Portal> temp = new ArrayList<Portal>();
		if (portal.size() <= 1) {
			Boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok);
			if (vaneleg) {
				p1 = new Portal();
				p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
				temp.add(p1);
				temp.add(p2);
			}
		}
		return temp;
	}



	/**
	 * A telepes lehelyez egy portált tehát beállí­tja a végpontját az aszteroidára amin állunk.
	 * 
	 * @param p a lehelyezendõ portál
	 */
	public void lehelyezPortal(Portal p) {
		Log.call();
		p.beallitVegpont(aszteroida);
	}


	/**
	 * A telepes törli magától a paraméterként megadott portált.
	 * 
	 * @param p a törlendõ portál
	 */
	public void torolPortal(Portal p) {
		Log.call();
		portal.remove(p);
	}



	/**
	 * Telepes visszarakja a nyersanyagot az aszteroidába ha az az aszteroida ki van fúrva és üreges
	 * a belseje.
	 * 
	 * @param ny visszarakandó nyersanyag
	 */
	public void visszarakNyersanyag(Nyersanyag ny) {
		Log.call();
		if (Cin.getBool("íœres és kifúrt?")) {
			nyersanyagok.remove(ny);
			aszteroida.hozzaadNyersanyag(ny);
		}
	}

	/**
	 * A telepes felrobban és meghí­vja magán a meghal függvényt.
	 */
	@Override
	public void Robbanas() {
		Log.call();
		Meghal();
	}

	/**
	 * A Telepes meghal, felrobbantja a portáljait (itt most csak removolja), és a nyersanyagait,
	 * majd meghí­vja a Szereplõ meghal függvényét,ami kitörli az aszteroidáról és törli a
	 * léptethetõk listájából. Végül meghí­vja a játékon a telepesmeghal függvényét.
	 */
	@Override
	public void Meghal() {
		Log.call();
		Log.debug("Telepes meghal");
		aszteroida.torolSzereplo(this);
		for (int i = 0; i < portal.size(); i++) {
			portal.get(i).Robbanas();
		}
		for (int i = 0; i < nyersanyagok.size(); i++) {
			nyersanyagok.get(i).Robbanas();
		}
		Jatek.torolLeptetheto(this);
		Jatek.telepesMeghal();
		// super.meghal??
	}

	public void Furas() {
		Log.call();
		aszteroida.Furas();
	}


	/**
	 * Telepes hozzáadja magához a paraméterként kapott nyersanyagot.
	 * 
	 * @param ny a hozzáadandó nyersanyag
	 */
	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		if (nyersanyagok.size() < 10) {
			nyersanyagok.add(ny);
		}
	}


	/**
	 * Telepes megkapja a paraméterként kapott Nyersanyagköltséget.
	 * 
	 * @param k A hozzáadandó nyersanyagköltség
	 */
	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		Log.call();
		epiteskoltseg.add(k);
	}


	/**
	 * A Telepes beállí­tja magának a paraméterként kaportt portált.
	 * 
	 * @param p A beállí­tandó portál
	 */
	public void setPortal(Portal p) {
		Log.call();
		portal.add(p);
	}


	public ArrayList<Portal> getPortal() {
		return portal;
	}

	public String toString() {
		String kimenet = Szkeleton.getID(aszteroida) + ":[";
		for (Nyersanyag nyersanyag : nyersanyagok) {
			kimenet += Szkeleton.getID(nyersanyag) + ":";
		}
		kimenet = kimenet.substring(0, kimenet.length() - 2);
		kimenet += "]:[";
		for (Portal port : portal) {
			kimenet += Szkeleton.getID(port) + ":";
		}
		kimenet = kimenet.substring(0, kimenet.length() - 2);
		kimenet += "]:";
		kimenet += String.valueOf(lepett);
		// kimenet += (char) 13 + (char) 10;
		return kimenet;
	}

	@Override
	public Boolean lepette() {
		return lepett;
	}

	@Override
	public void resetLepett() {
		lepett = false;
	}
}
