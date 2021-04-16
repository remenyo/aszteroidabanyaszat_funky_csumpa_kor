package src;

import java.util.ArrayList;
import java.util.Scanner;

// Telepes a Szerepl� lesz�rmazottja, ismeri a n�la l�v� port�lokat, nyersanyagokat �s
// a statikus �p�t�si k�lts�gek is ebbe az oszt�lyba tal�lhat�k.
public class Telepes extends Szereplo {
	private ArrayList<Nyersanyag> nyersanyagok; // A telepesn�l l�v� nyersanyagokat t�rolja.
	private static ArrayList<NyersanyagKoltseg> epiteskoltseg; // A port�l �s robot �p�t�s�nek a k�lts�g�t t�rolja.
	private ArrayList<Portal> portal;// A Telepesn�l l�v� port�lokat t�rolja.

	Telepes() {
		nyersanyagok = new ArrayList<Nyersanyag>();
		epiteskoltseg = new ArrayList<NyersanyagKoltseg>();
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
	public void Mozgas(int sorszam) {
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
		if(temp != null){
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

	/**
	 * Teszt alatt egyszer se h�v�dik meg de majd rendes m�k�d�s alatt itt v�laszthat a felhaszn�l�,
	 * hogy mit szeretne csin�lni a telepessel, �s a v�laszt�snak megfelel� f�ggv�ny fog megh�v�dni.
	 */
	@Override
	public void Lepes() {
		Log.call();
		Boolean elorejelzes = aszteroida.getElorejelzesvan();
		if(elorejelzes){
			System.out.println("Kovetkezo korbe napvihar lesz\n");
		}
		Scanner sc = new Scanner(System.in);
		//TODO 7 opciobol v�laszt�s bemenet alapj�n
	}

	/**
	 * A Telepes �tasdja a nyersanyagait az �p�t�sk�lts�g-nek ami megmondja, hogy van e el�g a robot
	 * �p�t�s�hez, ha van el�g akkor �p�t egy robotot �s r� utaztatja az aszteroid�ra amin �ll
	 * �ppen, ha nincs el�g nyersanyag akkor nem csin�l semmmit.
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
	 * Ha a Telepesn�l nincs m�r port�l, �tasdja a nyersanyagait az �p�t�sk�lts�g-nek ami megmondja,
	 * hogy van e el�g a port�l �p�t�s�hez, ha van el�g akkor �p�t egy port�l p�rt �s r� �s elrakja
	 * mag�nak, ha nincs el�g nyersanyag akkor nem csin�l semmmit, ha van n�la port�l akkor se
	 * csin�l semmit.
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
	 * A telepes lehelyez egy port�lt teh�t be�ll�tja a v�gpontj�t az aszteroid�ra amin �llunk.
	 * 
	 * @param p a lehelyezend� port�l
	 */
	public void lehelyezPortal(Portal p) {
		Log.call();
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
		if (Cin.getBool("�res �s kif�rt?")) {
			nyersanyagok.remove(ny);
			aszteroida.hozzaadNyersanyag(ny);
		}
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
	 * Telepes hozz�adja mag�hoz a param�terk�nt kapott nyersanyagot.
	 * 
	 * @param ny a hozz�adand� nyersanyag
	 */
	public void hozzaadNyersanyag(Nyersanyag ny) {
		Log.call();
		if(nyersanyagok.size()<10){
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
}
