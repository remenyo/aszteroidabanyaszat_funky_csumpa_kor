package src;

import java.util.ArrayList;

public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    public Szereplo() {

    }

    public void Mozgas(int sorszam) {
        Log.info("Meghívódott");
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this); 
        

    }

    public void Furas() {
        aszteroida.Furas();
    }

    public void Meghal() {
        aszteroida.torolSzereplo(this);
        Jatek.getInstance().torolLeptetheto(this);
    }

    public void Napvihar() {
        this.Meghal();
    }

    public void Robbanas() {

    }

    public ArrayList<Nyersanyag> getNyersanyagok() {
        return null;
    }

    public void Lepes() {
    }

    public void beallitAszteroida(Aszteroida a) {
        aszteroida = a;
    }
}
