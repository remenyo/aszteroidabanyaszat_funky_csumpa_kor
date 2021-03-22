package src;

import java.util.ArrayList;
import java.util.Scanner;

//Telepes a Szereplõ leszármazottja, ismeri a nála lévõ portálokat, nyersanyagokat és
//a statikus építési költségek is ebbe az osztályba találhatók.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok ; //A telepesnél lévõ nyersanyagokat tárolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg ; //A portál és robot építésének a költségét tárolja.
	private ArrayList<Portal> portal ;//A Telepesnél lévõ portálokat tárolja.
	
	
	Telepes(){
		nyersanyagok = new ArrayList<Nyersanyag>();
		epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
		portal = new ArrayList<Portal>();
		Jatek.telepesszam++;
	}
	
	//Meghívja a szereplõ mozgás függvényét amivel egy mésik aszteroidára utazik a telepes, majd
	//meghívja azon az aszteroidán az ellenõrizNyert fv-t hogy ellenõrizzük összegyült-e elég nyersanyag a játék megnyeréséhez.
	public void Mozgas(int sorszam) {
		Log.call();
		super.Mozgas(sorszam);
		aszteroida.ellenorizNyert();
	}
 
	//A telepes meghívja a Banyaszat() fv-t azon az aszteroidán amelyiken áll,
	//ha van nyersanyag az aszteroidába akkor azt magához veszi és meghívja az ellenõriznyertet az aszteroidán.
	public void Banyaszat() {
		Log.call();
		Nyersanyag temp = aszteroida.Banyaszat();
		if (Szkeleton.Kerdes("Van az aszteroidában nyersanyag?")) {
			nyersanyagok.add(temp);
			aszteroida.ellenorizNyert();
		}
	}

	//Visszaadja a telepesnél lévõ nyersanyagok listáját.
	public ArrayList<Nyersanyag> getNyersanyagok() {
		Log.call();
		return nyersanyagok;
	}

	//Teszt alatt egyszer se hívódik meg de majd rendes mûködés alatt 
	//itt választhat a felhasználó, hogy mit szeretne csinálni a telepessel,
	//és a választásnak megfelelõ függvény fog meghívódni.
	@Override
	public void Lepes() {
		Log.call();
	}

	//A Telepes átasdja a nyersanyagait az építésköltség-nek ami megmondja, hogy van e elég a robot építéséhez,
	//ha van elég akkor épít egy robotot és rá utaztatja az aszteroidára amin áll éppen,
	//ha nincs elég nyersanyag akkor nem csinál semmmit.
	public void epitRobot() {
		Log.call();
		boolean vaneleg = epiteskoltseg.get(1).koltsegSzamitas(nyersanyagok); 
		if (vaneleg) {
			Robot r = new Robot();
			aszteroida.Utazas(r);
		}
	}

	
	//Ha a Telepesnél nincs már portál, átasdja a nyersanyagait az építésköltség-nek ami megmondja,
	//hogy van e elég a portál építéséhez,
	//ha van elég akkor épít egy portál párt és rá és elrakja magának,
	//ha nincs elég nyersanyag akkor nem csinál semmmit, ha van néla portál akkor se csinál semmit.
	public void epitPortal() {
		Log.call();
		if (!Szkeleton.Kerdes("Van a telepesnél portálkapu?")) {
			boolean vaneleg = epiteskoltseg.get(0).koltsegSzamitas(nyersanyagok); 
			if (vaneleg) {
				Portal p1 = new Portal();
				Portal p2 = new Portal();
				portal.add(p1);
				portal.add(p2);
				p1.beallitPar(p2);
				p2.beallitPar(p1);
			}
		}
	}

	
	//A telepes lehelyez egy portált tehát beállítja a végpontját az aszteroidára amin állunk.
	public void lehelyezPortal(Portal p) {
		Log.call();
		p.beallitVegpont(aszteroida);
	}

	//A telepes törli magától a paraméterként megadott portált. 
	public void torolPortal(Portal p) {
		Log.call();
		portal.remove(p);
	}

	
	//Telepes visszarakja a nyersanyagot az aszteroidába ha az az aszteroida ki van fúrva és üreges a belseje.
	public void visszarakNyersanyag(Nyersanyag ny) {
		Log.call();
		if (Szkeleton.Kerdes("Üres és kifúrt?")) {
			nyersanyagok.remove(ny);
			aszteroida.hozzaadNyersanyag(ny);
		}
	}

	//A telepes felrobban és meghívja magán a meghal függvényt.
	@Override
	public void Robbanas() {
		Log.call();
		Meghal();
	}

	//A Telepes meghal, kitölri a portálját és nyersanyagát és meghívja a szereplõ meghal függvényét.
	@Override
	public void Meghal() {
		Log.call();
		Log.debug("Telepes meghal");
		portal.remove(0);
		nyersanyagok.remove(0);
		super.Meghal();
		Jatek.telepesMeghal();
	}

	//Telepes hozzáadja magához a paraméterként kapott nyersanyagot.
	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		nyersanyagok.add(ny);
	}

	//Telepes megkapja a paraméterként kapott Nyersanyagköltséget.
	public static void hozzaadKoltseg(NyersanyagKoltseg k) {
		Log.call();
		epiteskoltseg.add(k);
	}

	//A Telepes beállítja magának a paraméterként kaportt portált.
	public void setPortal(Portal p) {
		Log.call();
		portal.add(p);
	}
}
