package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    //Szereplõ létrejöttekor a léptethetõk listája is bõvül
    public Szereplo() {
    	Log.call();
        Jatek.leptethetok.add(this);
    }
    
    //Az aszteroida paraméterül kapott sorszámú szomszédjára utazik és a jelenlegirõl törli magát
    public void Mozgas(int sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    //Fúrja az aszteroidát, amin áll 
    public void Furas() {
        Log.call();
        aszteroida.Furas();
    }

    //Halálakor hívódik meg, törli az aszteroidáról és a léptethetõk közül
    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    //Amikor napvihar éri meghal
    public void Napvihar() {
        Log.call();
        Meghal();
    }

    //Robbanáskor hívódik meg, a leszármazottak valósítják meg
    public void Robbanas() {
        Log.call();
        // TODO üres?
    }

    //Telepes leszármazott visszaadja egyébként defaultan null-t ad vissza Robot miatt
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null;
    }

    //Õ jön a körben
    public void Lepes() {
        Log.call();
    }

    //Beállítja a paraméterül kapott aszteroidát jelenleginek
    public void beallitAszteroida(Aszteroida a) {
        Log.call();
        aszteroida = a;
    }
}
