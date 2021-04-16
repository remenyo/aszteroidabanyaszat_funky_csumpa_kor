package src;

import java.util.ArrayList;
import java.util.Scanner;

// Telepes a Szereplõ leszármazottja, ismeri a nála lévõ portálokat, nyersanyagokat és
// a statikus építési költségek is ebbe az osztályba találhatók.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok; // A telepesnél lévõ nyersanyagokat tárolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg; // A portál és robot építésének a költségét tárolja.
	private ArrayList<Portal> portal;// A Telepesnél lévõ portálokat tárolja.

	Telepes() {
		nyersanyagok = new ArrayList<Nyersanyag>();
		epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
		portal = new ArrayList<Portal>();
		Jatek.telepesszam++;
	}

	/**
	 * Meghívja a szereplõ mozgás függvényét amivel egy mésik aszteroidára utazik a telepes, majd
	 * meghívja azon az aszteroidán az ellenõrizNyert fv-t hogy ellenõrizzük összegyült-e elég
	 * nyersanyag a játék megnyeréséhez.
	 * 
	 * @param sorszam Az aszteroida azonosítója
	 */
	public void Mozgas(int sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}

	/**
	 * A telepes meghívja a Banyaszat() fv-t azon az aszteroidán amelyiken áll, ha van nyersanyag az
	 * aszteroidába akkor azt magához veszi és meghívja az ellenõriznyertet az aszteroidán.
	 */
	public void Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if(temp != null){
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

	/**
	 * Teszt alatt egyszer se hívódik meg de majd rendes mûködés alatt itt választhat a felhasználó,
	 * hogy mit szeretne csinálni a telepessel, és a választásnak megfelelõ függvény fog meghívódni.
	 */
	@Override
	public void Lepes() {
		Log.call();
		Boolean elorejelzes = aszteroida.getElorejelzesvan();
		if(elorejelzes){
			System.out.println("Kovetkezo korbe napvihar lesz\n");
		}
		Scanner sc = new Scanner(System.in);
		//TODO 7 opciobol választás bemenet alapján
	}

	/**
	 * A Telepes átasdja a nyersanyagait az építésköltség-nek ami megmondja, hogy van e elég a robot
	 * építéséhez, ha van elég akkor épít egy robotot és rá utaztatja az aszteroidára amin áll
	 * éppen, ha nincs elég nyersanyag akkor nem csinál semmmit.
	 */
	public void epitRobot() {
		Log.call();
		Boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok);
		if (vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
		}
	}


	/**
	 * Ha a Telepesnél nincs már portál, átasdja a nyersanyagait az építésköltség-nek ami megmondja,
	 * hogy van e elég a portál építéséhez, ha van elég akkor épít egy portál párt és rá és elrakja
	 * magának, ha nincs elég nyersanyag akkor nem csinál semmmit, ha van néla portál akkor se
	 * csinál semmit.
	 */
	public void epitPortal() {
		Log.call();
		if(portal.size()<=1){
			Boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok);
			if (vaneleg) {
				Portal p1 = new Portal();
				Portal p2 = new Portal();
				p1.beallitPar(p2);
				p2.beallitPar(p1);
			}
		}
	}



	/**
	 * A telepes lehelyez egy portált tehát beállítja a végpontját az aszteroidára amin állunk.
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
		if (Cin.getBool("Üres és kifúrt?")) {
			nyersanyagok.remove(ny);
			aszteroida.hozzaadNyersanyag(ny);
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
	 * A Telepes meghal, felrobbantja a portáljait (itt most csak removolja), és a nyersanyagait,
	 * majd meghívja a Szereplõ meghal függvényét,ami kitörli az aszteroidáról és törli a
	 * léptethetõk listájából. Végül meghívja a játékon a telepesmeghal függvényét.
	 */
	@Override
	public void Meghal() {
		Log.call();
		Log.debug("Telepes meghal");
		aszteroida.torolSzereplo(this);
		for(int i = 0; i< portal.size(); i++){
			portal.get(i).Robbanas();
		}
		for(int i = 0; i< nyersanyagok.size(); i++){
			nyersanyagok.get(i).Robbanas();
		}
		Jatek.torolLeptetheto(this);
		Jatek.telepesMeghal();
		//super.meghal??
	}


	/**
	 * Telepes hozzáadja magához a paraméterként kapott nyersanyagot.
	 * 
	 * @param ny a hozzáadandó nyersanyag
	 */
	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		if(nyersanyagok.size()<10){
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
	 * A Telepes beállítja magának a paraméterként kaportt portált.
	 * 
	 * @param p A beállítandó portál
	 */
	public void setPortal(Portal p) {
		Log.call();
		portal.add(p);
	}
}
