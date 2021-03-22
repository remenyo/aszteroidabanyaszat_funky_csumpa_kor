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
    
    //Aszteroida konstruktora végsõ verzióban nem így lesz tesztek kedvéért van jelenleg így
    Aszteroida(int reteg, boolean napkozel, Nap nap, Nyersanyag nyersanyag) {
        Log.ctor();
        this.reteg = reteg;
        this.napkozel = napkozel;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        this.nap = nap;
        this.nyersanyag = nyersanyag;
    }
    
    //vissza adja azt az egy szomszéd Hely-et aki az i. indexen áll.
    public Hely getSzomszed(int i) {
        Log.call();
        return szomszedok.get(i);
    }
    
    //Az aszteroida felrobbanás mindenkit megölve aki rajta áll és kivonva magát a szomszédai közül
    //az ifek a tesztek miatt kellettek ne fussunk indexOutOfBounds hibába.
    //robbanás után kiszedjük a nap elemei közül is ahol az összes aszteroida található
    public void Robbanas() {
        Log.call();
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
    
    //Napviharkor hívódik meg. Ha van benne nyersanyag vagy nincs átfúrva  a köpenye a rajta lévõ szereplõk meghalnak
    public void Napvihar() {
        Log.call();
        if (!Szkeleton.Kerdes("Ures az aszteroida es ki van furva?")) {
                szereplok.get(0).Napvihar();  
                szereplok.get(0).Napvihar();  //kétszer kell a 0áson mert amikor az elsõ elemen meghívódik akkor az kitörlõdik és az 1 es veszi át a helyét.
        }

    }
    
    //Fúráskor hívódik meg. Ha elfogy a köpeny a felszínre kerül az Aszteroida nyersanyaga
    public void Furas() {
        Log.call();
        reteg--;
        boolean elfogyott = Szkeleton.Kerdes("Elfogy a köpeny? (1:Igen 0:Nem)");
        if (elfogyott)
            nyersanyag.felszinreKerul(this);

    }
    
    //Ha egy aszteroida felrobban akkor a szomszédjain végig hívja ezt hogy töröljék ki õt a szomszédjaik közül
    //Ha egy aszteroidának nem marad szomszédja felrobban 
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (!Szkeleton.Kerdes("Maradt szomszéd?")) {
            szereplok.get(0).Meghal(); // Végsõ verzióban ez az összes az aszteroidán tartózkodó szereplõn meghívódik itt csak konkrét esetre.
            							//Robbanás elõttt azért kell mindenkin meghívni a meghalt mert a robbanásban a robot nem halna meg 
            							// hanem csak másik szomszédos aszteroidára kerülne de mivel nincs szomszéd ezért õ is meghal.
            Robbanas();
        }
    }
    
    //Ezzel veszünk fel egy Helyet az aszteroida szomszédjaihoz
    public void hozzaadSzomszed(Hely h) {
        Log.call();
        szomszedok.add(h);
    }
    
    //Bányászáskor hívódik meg a benne lévõ nyersanyagot adja vissza és üressé teszi magát
    public Nyersanyag Banyaszat() {
        Log.call();
        Nyersanyag visszaAdando = nyersanyag; //kimentjük az értéket
        nyersanyag = null; //üressé tesszük az aszteroidát
        return visszaAdando; // nem null értéket visszaadjuk.
    }
    
    //Ha az összes nyersanyag megtalálható az aszteroidán ami kell a játék megnyeréséhez a játék véget ér.
    public void ellenorizNyert() {
        Log.call();
        if (Szkeleton.Kerdes("Megvan az összes nyersanyag?")) {
            Jatek.jatekVegeNyert();
        }
    }
    
    //A rajta lévõ szereplõkhöz felveszünk egyet.
    public void hozzaadSzereplo(Szereplo sz) {
        Log.call();
        szereplok.add(sz);
    }
    
    //A rajta lévõ szereplõk közül eltávolítunk egyet
    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
    }
    
    //Az aszteroidát üressé tesszük. Nem kell neki paraméter hisz egy Aszteroidába egy nyersanyag lehet.
    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
    }
    
    //Az Aszteroidába belerakunk egy nyersanyagot ami a vissza rakás feltételei miatt a felszínre kerül egybõl
    //Ettõl fel is robbanhat ha Urán volt vagy el is párologhat ha vízjég.
    public void hozzaadNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
        ny.felszinreKerul(this);
    }
    
    
    //Mozgáskor hívódik meg. A szereplõnek azon tulajdonságát állítja át hogy melyik aszteroidán áll.
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
    }
    
    //A nyersanyagkoltséget (BillOfMaterial) állítja be. Jelen esteben hogy mi kell az ûrbázis felépítéséhez.
    public static void hozzaadUrbazisKoltseg(NyersanyagKoltseg k) {
        Log.call();
        urbazisKoltseg = k;
    }
    
    //Akkor hívódik meg ha egy szomszédja felrobbant és azt ki kell törölni a szomszédjai közül.
    public void szomszedRobbant(Aszteroida a) {
        Log.call();
        torolSzomszed(a);
    }
    
    //Napközelben beliség lekérdezése
    public boolean isNapkozelben() {
        Log.call();
        return Szkeleton.Kerdes("Napközelben van az aszteroida?");
    }
    
    //Az Aszteroidába beSettelünk egy nyersanyagot. Ilyenkor nem hívódik meg a felszíre kerülés ez a tesztekhez kellett.
    public void setNyersanyag(Nyersanyag ny) {
        Log.call();
        nyersanyag = ny;
    }
    
    //Vissza adja a szomszédok listáját.
    public ArrayList<Hely> getSzomszedok() {
        Log.call();
        return szomszedok;
    }

}
