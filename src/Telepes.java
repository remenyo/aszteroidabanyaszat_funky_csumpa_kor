package src;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

// Telepes a Szerepl� lesz�rmazottja, ismeri a n�la l�v� port�lokat, nyersanyagokat �s
// a statikus �p�t�si k�lts�gek is ebbe az oszt�lyba tal�lhat�k.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok; // A telepesn�l l�v� nyersanyagokat t�rolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg = new ArrayList<NyersanyagKoltseg>(); // A
																									// port�l
																									// �s
																									// robot
																									// �p�t�s�nek
																									// a
	// k�lts�g�t t�rolja.
	private ArrayList<Portal> portal;// A Telepesn�l l�v� port�lokat t�rolja.

	Telepes() {
		Log.ctor();
		nyersanyagok = new ArrayList<Nyersanyag>();
		portal = new ArrayList<Portal>();
		Jatek.telepesszam++;
	}

	/**
	 * Megh�vja a szerepl� mozg�s f�ggv�ny�t amivel egy m�sik aszteroid�ra utazik a telepes, majd
	 * megh�vja azon az aszteroid�n az ellen�rizNyert fv-t hogy ellen�rizz�k �sszegy�lt-e el�g
	 * nyersanyag a j�t�k megnyer�s�hez.
	 * 
	 * @param sorszam Az aszteroida azonos�t�ja
	 */
	public void Mozgas(Integer sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	/**
	 * A telepes megh�vja a Banyaszat() fv-t azon az aszteroid�n amelyiken �ll, ha van nyersanyag az
	 * aszteroid�ba akkor azt mag�hoz veszi �s megh�vja az ellen�riznyertet az aszteroid�n.
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
	 * Visszaadja a telepesn�l l�v� nyersanyagok list�j�t.
	 * 
	 * @return ArrayList<Nyersanyag> A telepesn�l l�v� nyersanyagok list�ja.
	 */
	@Override
	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.call();
		return nyersanyagok;
	}


	private void mozgasMenu() {
		Integer szomszeddb = aszteroida.getSzomszedok().size() + 1;
		Integer valasztas = Cin.getInt("Melyik helyre szeretne utazni az aszteroida " + szomszeddb
				+ " db szomsz�dja k�z�l? (1-" + szomszeddb);
		if ((valasztas > szomszeddb) && (valasztas <= 0)) {
			System.out.println("Nem j�t adott meg!");
		} else {
			Mozgas(valasztas - 1);
		}
	}

	private void nyersanyagvisszarakMenu() {
		if (!nyersanyagok.isEmpty()) {
			ArrayList<String> anyagLista = new ArrayList<String>(nyersanyagok.stream()
					.map(ny -> ny.getNev() + (ny.getNev().equals("Urán")
							? " (expozíciók száma:) " + ((Uran) ny).getnapFenyerte()
							: ""))
					.collect(Collectors.toList()));
			Integer valasz = -1 + Cin.kerdez_tobbvalasz("Melyik nyersanyagot szeretné visszarakni?",
					(String[]) anyagLista.toArray());
			if (valasz > 0)
				visszarakNyersanyag(nyersanyagok.get(valasz));
			else
				Log.jatek("Rossz válasz, nem történt visszahelyezés.");
		} else
			Log.jatek("Nincs nyersanyaga!");

	}


	/**
	 * Teszt alatt egyszer se h�v�dik meg de majd rendes m�k�d�s alatt itt v�laszthat a felhaszn�l�,
	 * hogy mit szeretne csin�lni a telepessel, �s a v�laszt�snak megfelel� f�ggv�ny fog megh�v�dni.
	 */
	@Override
	public void Lepes() {
		Log.call();
		Boolean elorejelzes = aszteroida.getElorejelzesvan();
		if (elorejelzes) {
			Log.jatek("Kovetkezo korbe napvihar lesz");
		}
		Integer valasz = Cin.kerdez_tobbvalasz("Képességek", "Mozgás", "Fúrás", "Bányászat",
				"Robotépítés és lehelyezés", "Teleportkapupár-építés", "Teleportkapu-lehelyezés",
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
					lerakPortal(portal.get(0));
				}
				break;
			case 7:
				nyersanyagvisszarakMenu();
				return;
			default:
				break;
		}
		// Scanner sc = new Scanner(System.in);
		// TODO 7 opciobol v�laszt�s bemenet alapj�n
	}

	/**
	 * A Telepes �tasdja a nyersanyagait az �p�t�sk�lts�g-nek ami megmondja, hogy van e el�g a robot
	 * �p�t�s�hez, ha van el�g akkor �p�t egy robotot �s r� utaztatja az aszteroid�ra amin �ll
	 * �ppen, ha nincs el�g nyersanyag akkor nem csin�l semmmit.
	 */
	public Robot epitRobot() {
		Log.call();
		Boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok);
		if (vaneleg) {
			Log.jatek("Robot Megépítve!");
			Robot r = new Robot();
			aszteroida.Utazas(r);
			return r;
		}else {
			Log.jatek("Nincs elég nyersanyagod");
		}
		return null;
	}


	/**
	 * Ha a Telepesn�l nincs m�r port�l, �tasdja a nyersanyagait az �p�t�sk�lts�g-nek ami megmondja,
	 * hogy van e el�g a port�l �p�t�s�hez, ha van el�g akkor �p�t egy port�l p�rt �s r� �s elrakja
	 * mag�nak, ha nincs el�g nyersanyag akkor nem csin�l semmmit, ha van n�la port�l akkor se
	 * csin�l semmit.
	 */
	public ArrayList<Portal> epitPortal() {
		Log.call();
		Portal p1;
		Portal p2;
		ArrayList<Portal> temp = null;
		if (portal.size() <= 1) {
			Boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok);
			if (vaneleg) {
				Log.jatek("Portál Megépítve!");
				temp = new ArrayList<Portal>();
				p1 = new Portal();
				p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
				temp.add(p1);
				temp.add(p2);
				portal.add(p1);
				portal.add(p2);
			}else {
				Log.jatek("Nincs elég nyersanyagod");
			}
		}
		return temp;
	}



	/**
	 * A telepes lehelyez egy port�lt teh�t be�ll�tja a v�gpontj�t az aszteroid�ra amin �llunk.
	 * 
	 * @param p a lehelyezend� port�l
	 */
	public void lerakPortal(Portal p) {
		Log.call();
		Log.jatek("Portál lerakva");
		p.beallitVegpont(aszteroida);
	}


	/**
	 * A telepes t�rli mag�t�l a param�terk�nt megadott port�lt.
	 * 
	 * @param p a t�rlend� port�l
	 */
	public void torolPortal(Portal p) {
		Log.call();
		portal.remove(p);
	}



	/**
	 * Telepes visszarakja a nyersanyagot az aszteroid�ba ha az az aszteroida ki van f�rva �s �reges
	 * a belseje.
	 * 
	 * @param ny visszarakand� nyersanyag
	 */
	public void visszarakNyersanyag(Nyersanyag ny) {
		Log.call();

		if (nyersanyagok.contains(ny)) { // Az a baj, hogy parancssorból akármilyen hülyeséget meg
											// lehetne adni, és kiakad.(játék közben is)
			Nyersanyag temp = ny;
			nyersanyagok.remove(ny);
			try{ 
				if (!aszteroida.hozzaadNyersanyag(ny))
					nyersanyagok.add(temp);
			} catch(Exception e) {
				
			}
			
		}
		/*
		 * if(!nyersanyagok.contains(ny)) return;
		 * 
		 * if (aszteroida.hozzaadNyersanyag(ny)) { nyersanyagok.remove(ny); }
		 */
	}

	/**
	 * A telepes felrobban �s megh�vja mag�n a meghal f�ggv�nyt.
	 */
	@Override
	public void Robbanas() {
		Log.call();
		Meghal();
	}

	/**
	 * A Telepes meghal, felrobbantja a port�ljait (itt most csak removolja), �s a nyersanyagait,
	 * majd megh�vja a Szerepl� meghal f�ggv�ny�t,ami kit�rli az aszteroid�r�l �s t�rli a
	 * l�ptethet�k list�j�b�l. V�g�l megh�vja a j�t�kon a telepesmeghal f�ggv�ny�t.
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
	 * Telepes hozz�adja mag�hoz a param�terk�nt kapott nyersanyagot.
	 * 
	 * @param ny a hozz�adand� nyersanyag
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
	 * Telepes megkapja a param�terk�nt kapott Nyersanyagk�lts�get.
	 * 
	 * @param k A hozz�adand� nyersanyagk�lts�g
	 */
	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		Log.call();
		epiteskoltseg.add(k);
	}


	/**
	 * A Telepes be�ll�tja mag�nak a param�terk�nt kaportt port�lt.
	 * 
	 * @param p A be�ll�tand� port�l
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
