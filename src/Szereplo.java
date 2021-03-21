package src;

import java.util.ArrayList;

public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    public Szereplo() {
    	Jatek.getInstance().leptethetok.add(this);
    }

    public void Mozgas(int sorszam) {
        Log.info("Meghívódott");
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this); 
    }

    public void Furas() {
    	Log.info("Meghivodott");
        aszteroida.Furas();
    }

    public void Meghal() {
    	Log.info("Meghivodott");
        aszteroida.torolSzereplo(this);
        Jatek.getInstance().torolLeptetheto(this);
    }

    public void Napvihar() {
    	Log.info("Meghivodott");
        this.Meghal();
    }

    public void Robbanas() {
    	Log.info("Meghivodott");

    }

    public ArrayList<Nyersanyag> getNyersanyagok() {
    	Log.info("Meghivodott");
        return null;
    }

    public void Lepes() {
    	Log.info("Meghivodott");
    }

    public void beallitAszteroida(Aszteroida a) {
    	Log.info("Meghivodott");
        aszteroida = a;
    }
}
