package src;

import java.util.ArrayList;

public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    public Szereplo() {
        Log.ctor();
        Jatek.leptethetok.add(this);
    }

    public void Mozgas(int sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    public void Furas() {
        Log.call();
        aszteroida.Furas();
    }

    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    public void Napvihar() {
        Log.call();
        this.Meghal();
    }

    public void Robbanas() {
        Log.call();

    }

    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null;
    }

    public void Lepes() {
        Log.call();
    }

    public void beallitAszteroida(Aszteroida a) {
        Log.call();
        aszteroida = a;
    }
}
