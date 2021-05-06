package src;

import java.util.ArrayList;

// Telepes a Szereplő leszármazottja, ismeri a nála lévő portálokat, nyersanyagokat és
// a statikus építési költségek is ebbe az osztályba találhatók.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok; // A telepesnél lévő nyersanyagokat tárolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>(); // A
																									// portol
																									// és
																									// robot
																									// építésének
																									// a
																									// költségtárolója.
	private ArrayList<Portal> portal;// A Telepesnél lévő portálokat tárolja.
	private Integer sorszam;

	/**
	 * Telepes konstruktora
	 */
	Telepes() {
		Log.ctor();
		jatekView = new TelepesView(this);
		nyersanyagok = new ArrayList<Nyersanyag>();
		portal = new ArrayList<Portal>();
		Jatek.telepesszam++;
	}

	public Integer getSorszam() {
		return sorszam;
	}

	public void setSorszam(Integer sorsz) {
		sorszam = sorsz;
	}

	/**
	 * Meghívja a szereplő mozgás függvényét amivel egy mésik aszteroidára utazik a telepes, majd
	 * meghívja azon az aszteroidán az ellenőrizNyert fv-t hogy ellenőrizzük összegyült-e elég
	 * nyersanyag a játék megnyeréséhez.
	 * 
	 * @param sorszam Az aszteroida azonosítója
	 */
	public void Mozgas(Integer sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	/**
	 * A telepes meghívja a Banyaszat() fv-t azon az aszteroidán amelyiken áll, ha van nyersanyag az
	 * aszteroidába akkor azt magához veszi és meghívja az ellenőriznyertet az aszteroidán.
	 */
	public void Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (temp != null) {
			Jatek.uzenet("Pacek ⛏️", "Kibányásztad: " + Szkeleton.getID((Nyersanyag) temp));
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		} else
			Jatek.uzenet("Jaj", "Ez a köröd elveszett.");
	}

	/**
	 * Visszaadja a telepesnél lévő nyersanyagok listáját.
	 * 
	 * @return nyersanyagok A telepesnél lévő nyersanyagok listája.
	 */
	@Override
	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.call();
		return nyersanyagok;
	}

	/**
	 * Megkérdezi a felhasználót, hogy melyik helyre szeretne utazni az aszteroida szomszédai közül,
	 * és a válasz alapján oda mozgatja a játékost.
	 */
	private void mozgasMenu() {
		Integer szomszeddb = aszteroida.getSzomszedok().size();
		Integer valasztas = Cin.getInt("Melyik helyre szeretne utazni az aszteroida " + szomszeddb
				+ " db szomszédja közel? (1-" + szomszeddb + ")");
		if ((valasztas > szomszeddb) || (valasztas <= 0)) {
			System.out.println("Nem jót adott meg!");
		} else {
			Mozgas(valasztas - 1);
		}
	}

	/**
	 * Megkérdezi a felhasználót, hogy melyik nyersanyagot rakja vissza a nála lévők közül, és a
	 * válasz alapján visszarakja azt.
	 */
	private void nyersanyagvisszarakMenu() {
		if (nyersanyagok.size() > 0) {
			System.out
					.println("Melyik nyersanyagot szeretné visszarakni? (sorszámával válaszoljon)");
			Integer db = 0;
			for (int i = 0; i < nyersanyagok.size(); i++) {
				if (nyersanyagok.get(i).getNev().equals("Urán")) {
					System.out.println(i + 1 + ". " + nyersanyagok.get(i).getNev()
							+ " napközelben felszínre kerülések száma: "
							+ ((Uran) nyersanyagok.get(i)).getnapFenyerte());
				} else {
					System.out.print(i + 1 + ". " + nyersanyagok.get(i).getNev());
				}
				db = i + 1;
			}
			Integer valasztas = Cin.getInt();
			if ((valasztas > db) || (valasztas <= 0)) {
				System.out.println("Nem jót adott meg!");
			} else {
				visszarakNyersanyag(nyersanyagok.get(valasztas - 1));
			}
		} else {
			System.out.println("Nincs nálá nyersanyag");
		}
	}

	/**
	 * Itt választhat a felhasználó, hogy mit szeretne csinálni a telepessel, és a választásnak
	 * megfelelő függvény fog meghívódni.
	 */
	@Override
	public void Lepes() {
		Jatek.enKorom(this);
		// synchronized (Jatek.lepesKesz) {
		// Jatek.lepesKesz.notify();
		// }
		/*
		 * Log.call(); Boolean elorejelzes = aszteroida.getElorejelzesvan(); if (elorejelzes) {
		 * Log.jatek("Kovetkezo korbe napvihar lesz"); } Integer valasz =
		 * Cin.kerdez_tobbvalasz("Képességek", "Mozgás", "Fúrás", "Bányászat",
		 * "Robotépítés és lehelyezés", "Teleportkapupár-építés", "Teleportkapu-lehelyezés",
		 * "Nyersanyag visszahelyezés"); switch (valasz) { case 1: mozgasMenu(); break; case 2:
		 * Furas(); break; case 3: Banyaszat(); break; case 4: epitRobot(); break; case 5:
		 * epitPortal(); break; case 6: if (!portal.isEmpty()) { lerakPortal(portal.get(0)); }
		 * break; case 7: nyersanyagvisszarakMenu(); return; default: break; }
		 */
	}

	/**
	 * A Telepes átasdja a nyersanyagait az építésköltség-nek ami megmondja, hogy van e elég a robot
	 * építéséhez, ha van elég akkor épít egy robotot és rá utaztatja az aszteroidára amin áll
	 * éppen, ha nincs elég nyersanyag akkor nem csinál semmmit.
	 * 
	 * @return a robot objektum ami építve lett ha sikerült null ha nem
	 */
	public Robot epitRobot() {
		Log.call();
		Boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok);
		if (vaneleg) {
			String id = "Robot_" + Szkeleton.getRobotID();
			Szkeleton.letrehoz("Robot", id);
			Robot r = (Robot) Szkeleton.getObj(id);
			aszteroida.Utazas(r);
			Jatek.uzenet("Robot 🤖 megépíve!",
					id + " Mostantól fúr vagy mozog és segít a célodban, legyen az bármi.");
			return r;
		} else {
			Log.debug("Nincs elég nyersanyag robot epitesehez");
		}
		return null;
	}

	/**
	 * Ha a Telepesnél nincs már portál, átasdja a nyersanyagait az építésköltség-nek ami megmondja,
	 * hogy van e elég a portál építéséhez, ha van elég akkor épít egy portál párt és rá és elrakja
	 * magának, ha nincs elég nyersanyag akkor nem csinál semmmit, ha van néla portál akkor se
	 * csinál semmit.
	 * 
	 * @return két portál egy listában
	 */
	public ArrayList<Portal> epitPortal() {
		Log.call();
		Portal p1;
		Portal p2;
		ArrayList<Portal> temp = null;
		if (portal.size() <= 1) {
			Boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok);
			if (vaneleg) {
				temp = new ArrayList<Portal>();
				p1 = new Portal();
				p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
				p1.setBirtokos(this);
				p2.setBirtokos(this);
				temp.add(p1);
				temp.add(p2);
				portal.add(p1);
				portal.add(p2);
			} else {
				Log.jatek("Nincs elég nyersanyag portálpár fabrikálásához");
			}
		}
		return temp;
	}

	/**
	 * A telepes lehelyez egy portált tehát beállítja a végpontját az aszteroidára amin állunk.
	 * 
	 * @param p a lehelyezendő portál
	 */
	public void lerakPortal(Portal p) {
		Log.call();
		Log.jatek("Portál lerakva");
		p.beallitVegpont(aszteroida);
	}

	/**
	 * A telepes törli magától a paraméterként megadott portált.
	 * 
	 * @param p a törlendő portál
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

		if (nyersanyagok.contains(ny)) {
			Nyersanyag temp = ny;
			nyersanyagok.remove(ny);
			try {
				if (!aszteroida.hozzaadNyersanyag(ny))
					nyersanyagok.add(temp);
			} catch (Exception e) {

			}

		}
	}

	/**
	 * A telepes felrobban és meghívja magán a meghal függvényt.
	 */
	@Override
	public void Robbanas() {
		Log.call();
		Meghal();
	}

	/**
	 * A Telepes meghal, felrobbantja a portáljait, és a nyersanyagait, majd, kitörli az
	 * aszteroidáról és léptethetők listájából magát. Végül meghívja a játékon a telepesmeghal
	 * függvényét.
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
	}

	/**
	 * Telepes kifúrja az aszteroidát.
	 */
	public Boolean Furas() {
		Log.call();
		return aszteroida.Furas();
	}

	/**
	 * Telepes hozzáadja magához a paraméterként kapott nyersanyagot.
	 * 
	 * @param ny a hozzáadandó nyersanyag
	 */
	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		if (ny == null)
			return;
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
	 * A Telepes beállítja magának a paraméterként kapott portált.
	 * 
	 * @param p A beállítandó portál
	 */
	public void setPortal(Portal p) {
		Log.call();
		portal.add(p);
	}

	/**
	 * Visszaadja a telepes portál listáját
	 * 
	 * @return portal lista
	 */
	public ArrayList<Portal> getPortal() {
		return portal;
	}

	/**
	 * Visszaadja az adatait a telepesnek.
	 * 
	 * @return telepesek adatai
	 */
	public String toString() {
		String kimenet = Szkeleton.getID(aszteroida) + ":[";
		if (nyersanyagok.size() != 0) {
			for (Nyersanyag nyersanyag : nyersanyagok) {
				kimenet += Szkeleton.getID(nyersanyag) + ":";
			}
			kimenet = kimenet.substring(0, kimenet.length() - 1);
		} else {
			kimenet += "null";
		}
		kimenet += "]:[";
		for (Portal port : portal) {
			kimenet += Szkeleton.getID(port) + ":";
		}
		if (portal.size() != 0)
			kimenet = kimenet.substring(0, kimenet.length() - 1);
		else {
			kimenet += "null";
		}
		kimenet += "]:";
		kimenet += String.valueOf(lepett);
		return kimenet;
	}

}
