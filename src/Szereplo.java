package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;

    //Szerepl� l�trej�ttekor a l�ptethet�k list�ja is b�v�l
    public Szereplo() {
    	Log.call();
        Jatek.leptethetok.add(this);
    }
    
    //Az aszteroida param�ter�l kapott sorsz�m� szomsz�dj�ra utazik �s a jelenlegir�l t�rli mag�t
    public void Mozgas(int sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    //F�rja az aszteroid�t, amin �ll 
    public void Furas() {
        Log.call();
        aszteroida.Furas();
    }

    //Hal�lakor h�v�dik meg, t�rli az aszteroid�r�l �s a l�ptethet�k k�z�l
    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    //Amikor napvihar �ri meghal
    public void Napvihar() {
        Log.call();
        Meghal();
    }

    //Robban�skor h�v�dik meg, a lesz�rmazottak val�s�tj�k meg
    public void Robbanas() {
        Log.call();
        // TODO �res?
    }

    //Telepes lesz�rmazott visszaadja egy�bk�nt defaultan null-t ad vissza Robot miatt
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null;
    }

    //� j�n a k�rben
    public void Lepes() {
        Log.call();
    }

    //Be�ll�tja a param�ter�l kapott aszteroid�t jelenleginek
    public void beallitAszteroida(Aszteroida a) {
        Log.call();
        aszteroida = a;
    }
}
