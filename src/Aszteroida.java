package src;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Danesz
 */
public class Aszteroida extends Hely {
    private Integer reteg;
    private Boolean napkozel;
    private Boolean elorejelzesvan;
    private ArrayList<Szereplo> szereplok;
    private ArrayList<Hely> szomszedok;
    private Nap nap;
    private Nyersanyag nyersanyag;
    private static NyersanyagKoltseg urbazisKoltseg;

    /**
     * Aszteroida konstruktor
     * 
     * @param reteg Az aszteroidán lévő kőréteg kezdeti vastagsága (0-sok)
     * @param napkozel Napközelben van-e az aszteroida
     * @param nap A nap referenciája
     * @param nyersanyag Az aszteroida magjában található nyersanyag (opcoinális)
     */
    Aszteroida(Nap nap) {
        this.nap = nap;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        elorejelzesvan = false;
    }

    /**
     * Vissza adja azt az egy szomszéd Hely-et aki az i. indexen áll.
     * 
     * @param i A szomszéd azonosítója
     * @return A szomszéd Hely aki az i. indexen áll.
     */
    public Hely getSzomszed(int i) {
        Log.call();
        try {
            return szomszedok.get(i);
        } catch (Exception e) {
            System.out.println("Nincs ilyen aszteroida");
            return this; // "onmagara megy"
        }

    }

    /**
     * Az aszteroida robbanás minden rajta tartózkodó entitást felrobbantja majd kivonva magát a
     * szomszédai közül, majd a játékból.
     */
    public void Robbanas() {
        Log.call();
        // TODO rossz a sorrend, előbb robbantjuk a robotokat, aztán töröljük a szomszédokat
        // TODO az ifek csak a tesztek miatt kellettek ne fussunk indexOutOfBounds hibába.
        if (nyersanyag != null) {
            nyersanyag.Robbanas();
        }
        for (Hely h : szomszedok) {
            h.szomszedRobbant(this);
        }
        for (Szereplo sz : szereplok) {
            sz.Robbanas();
        }
        nap.torolAszteroida(this);

    }

    /**
     * Napvihar esetén hívódik meg. Ha van benne nyersanyag, vagy nincs átfúrva az aszteroida
     * köpenye, az entitásokon is meghívódik a Napvihar függvény.
     * 
     * @see Nap
     */
    public void Napvihar() {
        Log.call();
        elorejelzesvan = false;
        for (Hely h : szomszedok) {
            h.szomszedNapvihar();
        }
        if (reteg != 0 || nyersanyag != null) {
        	/*Iterator<Szereplo> i = szereplok.iterator();
        	while (i.hasNext()) {
        		Szereplo s = i.next();
        		s.Napvihar();
        	}*/
        	//szereplok.forEach(it->{it.Napvihar();});
        	//ArrayList<Szereplo> szereplokTemp = szereplok;
        	//try {
        //	int s = szereplok.size();
        	//int i = 0;
        	//while(i<szereplok.size()) {
        	//	szereplokTemp.get(i++).Napvihar();
        	//}
        		
            	//for (int i = 0; i<s;i++) {
                
                	
            //    }
        	//}catch(Exception e){
        	//	
        	//}*/
        	ArrayList<Szereplo> temp = szereplok;
            int k=0;
            for(int i=0; i<temp.size(); i++) {
                if(temp.get(i)!=szereplok.get(k)) {
                    k++;
                }
                szereplok.get(k).Napvihar();
            }
        	
        }
    }

    /**
     * Az aszteroida ezzel a függvényel fúrható. Ha elfogy a köpeny, felszínre kerül az Aszteroida
     * nyersanyaga.
     */
    public void Furas() {
        Log.call();
        if (reteg > 0) {
            reteg--;
            if (reteg == 0 || nyersanyag!= null) {
                nyersanyag.felszinreKerul(this);
            }
        }
    }

    /**
     * Egy Aszteroida-t töröl a szomszédok listából.
     * 
     * Ha egy aszteroida felrobban, akkor a szomszédjain végig hívja ezt hogy töröljék ki őt a
     * szomszédjaik közül Ha egy aszteroidának nem marad szomszédja felrobban
     * 
     * @param h a törlendő aszteroida
     */
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (szomszedok.size() == 0) {
            for (Szereplo sz : szereplok) {
                sz.Meghal();
            }
            Robbanas(); // Robbanás előttt azért kell mindenkin meghívni a meghalt
                        // mert a robbanásban a robot nem halna meg
                        // hanem csak másik szomszédos aszteroidára kerülne de mivel
                        // nincs szomszéd ezért ő is meghal.

        }
    }

    /**
     * Aszteroida hozzáadása a szomszédok listához.
     * 
     * @param h A hozzáadadndó hely.
     */
    public void hozzaadSzomszed(Hely h) {
        Log.call();
        szomszedok.add(h);
    }

    public void setElorejelzesvan() {
        elorejelzesvan = true;
    }

    public Boolean getElorejelzesvan() {
        return elorejelzesvan;
    }

    public void setReteg(Integer ujReteg) {
        reteg = ujReteg;
    }

    // Nap mozgása miatt kerül be az ős függvényének megvalósítása
    public void utazasHely(Hely hely) {
        hozzaadSzomszed(hely);
    }

    /**
     * Bányászáskor hívódik meg a benne lévő nyersanyagot adja vissza és üressé teszi magát
     * 
     * @return A kibányászott nyersanyag. Ha nem sikerül a múvelet, {@code null}-al tér vissza.
     */
    public Nyersanyag Banyaszat() {
        Log.call();
        if(reteg==0 && nyersanyag !=null) {
        	Nyersanyag visszaAdando = nyersanyag; // kimentjük az értéket
            torolNyersanyag(); // üressé tesszük az aszteroidát
            return visszaAdando; // nem null értéket visszaadjuk.
        }
        return null;
    }

    /**
     * A játék "megnyert"-ségét ellenőrzi az aszteroidán.
     * 
     * Ha az összes nyersanyag megtalálható az aszteroidán ami kell a játék megnyeréséhez, a játék
     * véget ér.
     */
    public void ellenorizNyert() {
        Log.call();
        ArrayList<Nyersanyag> nyLista = new ArrayList<Nyersanyag>();
        for (Szereplo sz : szereplok) {
            nyLista.addAll(sz.getNyersanyagok());
        }
        if (urbazisKoltseg.koltsegSzamitas(nyLista)) {
            Jatek.jatekVegeNyert();
        }
    }

    /**
     * Hozzáad a szereplők listához egy {@code Szereplő}-t
     * 
     * @param sz Az új szereplő
     */
    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }

    /**
     * Eltávolít a szereplők listából egy {@code Szereplő}-t
     * 
     * @param sz A törlendő szereplő
     */
    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
    }

    /**
     * Az aszteroida magjában található nyersanyagot elfelejti, (ezzel törölve azt.)
     * 
     * Nem kell neki paraméter hisz egy Aszteroidába egy nyersanyag lehet.
     */
    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
    }

    /**
     * Az Aszteroida magjába belerak egy nyersanyagot.
     * 
     * (A vissza rakás feltételei miatt a felszínre is kerül egyből. Ettől fel is robbanhat, ha pl.
     * Urán volt, vagy el is párologhat, ha vízjég.)
     * 
     * @param ny Az aszteroida magjába helyezendő nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }

    /**
     * Mozgáskor hívódik meg. A szereplőt átutaztatja erre az aszteroidára.
     * 
     * @param sz Az utazó szereplő
     */
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
    }

    /**
     * A nyersanyagkoltséget {@code BillOfMaterial} állítja be.
     * 
     * Jelen esteben hogy mi kell az űrbázis felépítéséhez.
     * 
     * @param k A beállítandó {@code NyersanyagKoltseg}
     */
    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
        Log.call();
        urbazisKoltseg = k;
    }

    /**
     * Akkor hívódik meg ha egy szomszédja felrobbant és azt ki kell törölni a szomszédjai közül.
     */
    public void szomszedRobbant(Aszteroida a) {
        Log.call();
        torolSzomszed(a);
    }

    /**
     * Napközelbenség lekérdezése
     * 
     * @return Aszteroida napközelsége
     */
    public boolean isNapkozelben() {
        Log.call();
        return napkozel;
    }

    public void setNapkozel(Boolean ujNapkozel) {
        napkozel = ujNapkozel;
    }

    /**
     * Az Aszteroidába beSettelünk egy nyersanyagot. Ilyenkor nem hívódik meg a felszíre kerülés.
     * 
     * @param ny Beállítandó nyersanyag
     */
    public void setNyersanyag(Nyersanyag ny) {
        // TODO ez a tesztekhez kellett. DE KELL IS! lesz teszt még
        Log.call();
        nyersanyag = ny;
    }

    /**
     * Az aszteroida szomszédjainak listáját adja vissza.
     * 
     * @return Szomszédok listája
     */
    public ArrayList<Hely> getSzomszedok() {
        Log.call();
        return szomszedok;
    }

    public String toString() {
        String kimenet = reteg.toString() + ":" + napkozel.toString();

        kimenet += ":[";
        for (Szereplo szereplo : szereplok) {
            kimenet += Szkeleton.getID(szereplo) + ":";
        }
        kimenet = kimenet.substring(0, kimenet.length() - 2);
        kimenet += "]:[";
        for (Hely szomszed : szomszedok) {
            kimenet += Szkeleton.getID(szomszed) + ":";
        }
        kimenet = kimenet.substring(0, kimenet.length() - 2);
        kimenet += "]:" + Szkeleton.getID(nyersanyag);
        // kimenet += "" /* + (char) 13 + (char) 10 */;
        return kimenet;

    }

}
