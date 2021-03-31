package src;

import java.util.ArrayList;

/**
 *
 * @author Danesz
 */
public class Aszteroida extends Hely {
    private int reteg;
    private boolean napkozel;
    private ArrayList<Szereplo> szereplok;
    private ArrayList<Hely> szomszedok;
    private Nap nap;
    private Nyersanyag nyersanyag;
    private static NyersanyagKoltseg urbazisKoltseg;

    /**
     * Aszteroida konstruktor
     * 
     * @param reteg      Az aszteroidán lévõ kõréteg kezdeti vastagsága (0-sok)
     * @param napkozel   Napközelben van-e az aszteroida
     * @param nap        A nap referenciája
     * @param nyersanyag Az aszteroida magjában található nyersanyag (opcoinális)
     */
    Aszteroida(int reteg, boolean napkozel, Nap nap, Nyersanyag nyersanyag) {
        Log.ctor();
        // TODO Aszteroida konstruktora végsõ verzióban nem így lesz tesztek kedvéért van jelenleg
        // így
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }

    /**
     * Vissza adja azt az egy szomszéd Hely-et aki az i. indexen áll.
     * 
     * @param i A szomszéd azonosítója
     * @return A szomszéd Hely aki az i. indexen áll.
     */
    public Hely getSzomszed(int i) {
        Log.call();
        return szomszedok.get(i);
    }

    /**
     * Az aszteroida robbanás minden rajta tartózkodó entitást felrobbantja majd kivonva magát a
     * szomszédai közül, majd a játékból.
     */
    public void Robbanas() {
        Log.call();
        // TODO rossz a sorrend, elõbb robbantjuk a robotokat, aztán töröljük a szomszédokat
        // TODO az ifek csak a tesztek miatt kellettek ne fussunk indexOutOfBounds hibába.
        nyersanyag.Robbanas();// nem e null
        if (szomszedok.size() != 0) {

            szomszedok.get(0).szomszedRobbant(this);
            if (szomszedok.size() == 2) {

                szomszedok.get(1).szomszedRobbant(this);
            }
        }
        if (szereplok.size() != 0) {
            szereplok.get(0).Robbanas();
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
        if (!Szkeleton.Kerdes("Ures az aszteroida es ki van furva?")) {
            szereplok.get(0).Napvihar();
            szereplok.get(0).Napvihar(); // kétszer kell a 0áson mert amikor az elsõ elemen
                                         // meghívódik akkor az kitörlõdik és az 1 es veszi át a
                                         // helyét.
        }
    }

    /**
     * Az aszteroida ezzel a függvényel fúrható. Ha elfogy a köpeny, felszínre kerül az Aszteroida
     * nyersanyaga.
     */
    public void Furas() {
        Log.call();
        reteg--;
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a köpeny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }

    /**
     * Egy Aszteroida-t töröl a szomszédok listából.
     * 
     * Ha egy aszteroida felrobban, akkor a szomszédjain végig hívja ezt hogy töröljék ki õt a
     * szomszédjaik közül Ha egy aszteroidának nem marad szomszédja felrobban
     * 
     * @param h a törlendõ aszteroida
     */
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomszéd?")) {
            szereplok.get(0).Meghal(); // Végsõ verzióban ez az összes az aszteroidán tartózkodó
                                       // szereplõn meghívódik itt csak konkrét esetre.
                                       // Robbanás elõttt azért kell mindenkin meghívni a meghalt
                                       // mert a robbanásban a robot nem halna meg
                                       // hanem csak másik szomszédos aszteroidára kerülne de mivel
                                       // nincs szomszéd ezért õ is meghal.
            Robbanas();
        }
    }

    /**
     * Aszteroida hozzáadása a szomszédok listához.
     * 
     * @param h A hozzáadadndó hely.
     */
    public void hozzaadSzomszed(Hely h) { // TODO miért hely? nem csak aszteroidát tárolunk a
                                          // szomszédokban?
        Log.call();
        szomszedok.add(h);
    }

    /**
     * Bányászáskor hívódik meg a benne lévõ nyersanyagot adja vissza és üressé teszi magát
     * 
     * @return A kibányászott nyersanyag. Ha nem sikerül a múvelet, {@code null}-al tér vissza.
     */
    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag; // kimentjük az értéket
        nyersanyag = null; // üressé tesszük az aszteroidát
        return visszaAdando; // nem null értéket visszaadjuk.
    }

    /**
     * A játék "megnyert"-ségét ellenõrzi az aszteroidán.
     * 
     * Ha az összes nyersanyag megtalálható az aszteroidán ami kell a játék megnyeréséhez, a játék
     * véget ér.
     */
    public void ellenorizNyert() {
        Log.call();
        if (Szkeleton.Kerdes("Megvan az összes nyersanyag?")) {
            Jatek.jatekVegeNyert();
        }
    }

    /**
     * Hozzáad a szereplõk listához egy {@code Szereplõ}-t
     * 
     * @param sz Az új szereplõ
     */
    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }

    /**
     * Eltávolít a szereplõk listából egy {@code Szereplõ}-t
     * 
     * @param sz A törlendõ szereplõ
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
     * (A vissza rakás feltételei miatt a felszínre is kerül egybõl. Ettõl fel is robbanhat, ha pl.
     * Urán volt, vagy el is párologhat, ha vízjég.)
     * 
     * @param ny Az aszteroida magjába helyezendõ nyersanyag
     */
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }

    /**
     * Mozgáskor hívódik meg. A szereplõt átutaztatja erre az aszteroidára.
     * 
     * @param sz Az utazó szereplõ
     */
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
        // TODO furi ez a függvény TBH
    }

    /**
     * A nyersanyagkoltséget {@code BillOfMaterial} állítja be.
     * 
     * Jelen esteben hogy mi kell az ûrbázis felépítéséhez.
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
        return Szkeleton.Kerdes("Napközelben van az aszteroida?");
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

}
