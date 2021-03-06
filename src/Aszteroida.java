package src;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class Aszteroida extends Hely {
    private Integer reteg;
    private Boolean napkozel;
    private Boolean elorejelzesvan;
    private ArrayList<Szereplo> szereplok;
    private ArrayList<Hely> szomszedok;
    private Nap nap;
    private Nyersanyag nyersanyag;
    private static NyersanyagKoltseg urbazisKoltseg;
    // private AszteroidaView aszteroidaView;
    private Integer sorszam;

    /**
     * Aszteroida konstruktor
     * 
     * @param reteg Az aszteroidán lévő kőréteg kezdeti vastagsága (0-sok)
     * @param napkozel Napközelben van-e az aszteroida
     * @param nap A nap referenciája
     * @param nyersanyag Az aszteroida magjában található nyersanyag (opcoinális)
     */
    Aszteroida(Nap nap, AszteroidaView aszteroidaView) {
        this.nap = nap;
        szereplok = new ArrayList<Szereplo>();
        szomszedok = new ArrayList<Hely>();
        elorejelzesvan = false;
        nap.hozzaadAszteroida(this);
        jatekView = aszteroidaView;

    }

    public JatekView getAszteroidaView() {
        return jatekView;
    }

    public Integer getReteg() {
        return reteg;
    }

    public JatekView getView() {
        return null;
    }

    public Nyersanyag getNyersanyag() {
        return nyersanyag;
    }

    public Aszteroida szomszedosAszteroida() {
        return this;
    }

    public ArrayList<JatekView> getAllView() {
        ArrayList<JatekView> osszes = new ArrayList<JatekView>();
        for (Szereplo szereplo : szereplok) {
            JatekView nezet = szereplo.getView();
            if (nezet != null)
                osszes.add(nezet);
        }
        for (Hely szomszed : szomszedok) {
            JatekView nezet = szomszed.getView();
            if (nezet != null)
                osszes.add(nezet);
        }
        if (nyersanyag != null)
            osszes.add(nyersanyag.getView());
        return osszes;
    }

    /**
     * Vissza adja azt az egy szomszéd Hely-et aki az i. indexen áll.
     * 
     * @param i A szomszéd azonosítója
     * @return A szomszéd Hely aki az i. indexen áll.
     */
    public Hely getSzomszed(Integer i) {
        Log.call();
        try {
            return szomszedok.get(i);
        } catch (Exception e) {
            Log.debug("Nincs " + (i + 1) + "db szomszédos aszteroida.");
            return this; // "onmagara megy"
        }

    }

    /**
     * Az aszteroida robbanás minden rajta tartózkodó entitást felrobbantja majd kivonva magát a
     * szomszédai közül, majd a játékból.
     */
    public void Robbanas() {
        Log.call();
        nap.torolAszteroida(this);
        if (nyersanyag != null) {
            nyersanyag.Robbanas();
        }
        for (Hely h : szomszedok) {
            h.szomszedRobbant(this);
        }
        // mindenkin végig kell menni mivel mindenki vagy átmegy máshová vagy meghal így
        // az első
        // elem mindig más lesz.
        int eredeti = szereplok.size();
        for (int i = 0; i < eredeti; i++) {
            szereplok.get(0).Robbanas();
        }

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

            int s = szereplok.size();
            try {
                for (int i = 0; i < s; i++) {
                    szereplok.get(0).Napvihar();
                }
            } catch (ConcurrentModificationException e) {

            }

        }
        jatekView.UpdateView();
    }

    /**
     * Az aszteroida ezzel a függvényel fúrható. Ha elfogy a köpeny, felszínre kerül az Aszteroida
     * nyersanyaga.
     */
    public Boolean Furas() {
        Log.call();
        Boolean sikeres = false;
        if (reteg > 0) {
            reteg--;
            if (reteg == 0 && nyersanyag != null) {
                nyersanyag.felszinreKerul(this);
            }
            sikeres = true;
            Log.debug("Sikeres fúrás");
        } else
            Log.debug("Sikertelen fúrás");
        jatekView.UpdateView();
        return sikeres;
    }

    /**
     * Egy Aszteroida-t töröl a szomszédok listából. Ha egy aszteroida felrobban, akkor a
     * szomszédjain végig hívja ezt hogy töröljék ki őt a szomszédjaik közül Ha egy aszteroidának
     * nem marad szomszédja felrobban
     * 
     * @param h a törlendő aszteroida
     */
    public void torolSzomszed(Hely h) {
        Log.call();
        szomszedok.remove(h);
        if (szomszedok.size() == 0) {
            int eredeti = szereplok.size();
            for (int i = 0; i < eredeti; i++) {
                szereplok.get(0).Meghal();
            }

            Robbanas(); // Robbanás előttt azért kell mindenkin meghívni a meghalt
                        // mert a robbanásban a robot nem halna meg
                        // hanem csak másik szomszédos aszteroidára kerülne de mivel
                        // nincs szomszéd ezért ő is meghal.
        }
        jatekView.UpdateView();
    }

    /**
     * Aszteroida hozzáadása a szomszédok listához.
     * 
     * @param h A hozzáadadndó hely.
     */
    public void hozzaadSzomszed(Hely h) {
        Log.call();
        szomszedok.add(h);
        jatekView.UpdateView();
    }

    /**
     * A következő körben várható napvihar jelzés beállítására használható.
     */
    public void setElorejelzesvan() {
        elorejelzesvan = true;
    }

    /**
     * A következő körben várható napvihar lekérdezésére használható.
     * 
     * @return Boolean a következő körben napvihar várható
     */
    public Boolean getElorejelzesvan() {
        return elorejelzesvan;
    }

    /**
     * A rétegvastagságot beállítja a megadott értékre.
     * 
     * @param ujReteg Az új rétegvastagság
     */
    public void setReteg(Integer ujReteg) {
        if (ujReteg < 0)
            Log.debug("Negatív rétegvastagság!");
        else
            reteg = ujReteg;
    }

    /**
     * Nap mozgása miatt kerül be az ős függvényének megvalósítása
     */
    public void utazasHely(Portal hely) {
        hozzaadSzomszed(hely);
        hely.setVegpont(this);
        jatekView.UpdateView();
    }

    /**
     * Bányászáskor hívódik meg a benne lévő nyersanyagot adja vissza és üressé teszi magát
     * 
     * @return A kibányászott nyersanyag. Ha nem sikerül a múvelet, {@code null}-al tér vissza.
     */
    public Nyersanyag Banyaszat() {
        Log.call();
        if (reteg == 0 && nyersanyag != null) {
            Nyersanyag visszaAdando = nyersanyag; // kimentjük az értéket
            torolNyersanyag(); // üressé tesszük az aszteroidát
            Log.jatek(visszaAdando.getNev() + " kibányászva");
            return visszaAdando; // nem null értéket visszaadjuk.
        } else if (reteg > 0) {
            Log.jatek("Még van kéreg");
            return null;
        } else {
            Log.jatek("Üres az aszteroida");
            return null;
        }
    }

    /**
     * A játék "megnyert"-ségét ellenőrzi az aszteroidán. Ha az összes nyersanyag megtalálható az
     * aszteroidán ami kell a játék megnyeréséhez, a játék véget ér.
     */
    public void ellenorizNyert() {
        Log.call();
        ArrayList<Nyersanyag> nyLista = new ArrayList<Nyersanyag>();
        for (Szereplo sz : szereplok) {
            if (sz.getNyersanyagok() != null) {
                nyLista.addAll(sz.getNyersanyagok());
            }
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
        jatekView.UpdateView();
    }

    /**
     * Eltávolít a szereplők listából egy {@code Szereplő}-t
     * 
     * @param sz A törlendő szereplő
     */
    public void torolSzereplo(Szereplo sz) {
        Log.call();
        szereplok.remove(sz);
        jatekView.UpdateView();
    }

    /**
     * Az aszteroida magjában található nyersanyagot elfelejti, (ezzel törölve azt.) Nem kell neki
     * paraméter hisz egy Aszteroidába egy nyersanyag lehet.
     */
    public void torolNyersanyag() {
        Log.call();
        nyersanyag = null;
        jatekView.UpdateView();
    }

    /**
     * Az Aszteroida magjába belerak egy nyersanyagot. (A vissza rakás feltételei miatt a felszínre
     * is kerül egyből. Ettől fel is robbanhat, ha pl. Urán volt, vagy el is párologhat, ha vízjég.)
     * 
     * @param ny Az aszteroida magjába helyezendő nyersanyag
     */
    public Boolean hozzaadNyersanyag(Nyersanyag ny) throws Exception {
        Log.call();
        if (reteg == 0 && nyersanyag == null) {
            Log.jatek("Nyersanyag visszahelyezve");
            nyersanyag = ny;
            ny.felszinreKerul(this);
            jatekView.UpdateView();
            return true;
        }
        Log.jatek("Nyersanyag nem lett visszahelyezve");
        jatekView.UpdateView();
        return false;
    }

    /**
     * Mozgáskor hívódik meg. A szereplőt átutaztatja erre az aszteroidára. Portállal való utazásnak
     * is ez a vége.
     * 
     * @param sz Az utazó szereplő
     */
    public void Utazas(Szereplo sz) {
        Log.call();
        sz.beallitAszteroida(this);
        Log.jatek("Átutaztál egy másik aszteroidára");
        jatekView.UpdateView();
    }

    /**
     * A nyersanyagkoltséget {@code BillOfMaterial} állítja be. Jelen esteben hogy mi kell az
     * űrbázis felépítéséhez.
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
    public Boolean isNapkozelben() {
        Log.call();
        return napkozel;
    }

    /**
     * Beállítja a napkozel attribútumot
     * 
     * @param ujNapkozel
     */
    public void setNapkozel(Boolean ujNapkozel) {
        napkozel = ujNapkozel;
    }

    /**
     * Az Aszteroidába beSettelünk egy nyersanyagot. Ilyenkor nem hívódik meg a felszíre kerülés.
     * 
     * @param ny Beállítandó nyersanyag
     */
    public void setNyersanyag(Nyersanyag ny) {
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
        if (szereplok.size() != 0)
            kimenet = kimenet.substring(0, kimenet.length() - 1);
        else {
            kimenet += "null";
        }
        kimenet += "]:[";
        for (Hely szomszed : szomszedok) {
            kimenet += Szkeleton.getID(szomszed) + ":";
        }
        if (szomszedok.size() != 0)
            kimenet = kimenet.substring(0, kimenet.length() - 1);
        else {
            kimenet += "null";
        }
        kimenet += "]:";
        if (nyersanyag != null)
            kimenet += Szkeleton.getID(nyersanyag);
        else
            kimenet += "null";
        return kimenet;

    }
    /**
     * Aszteroida nevét adja vissza.
     * @return
     */
    public String getNev() {
        return Szkeleton.getID(this);
    }

}
