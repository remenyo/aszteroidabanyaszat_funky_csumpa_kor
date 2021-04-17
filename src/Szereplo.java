package src;

import java.util.ArrayList;

abstract public class Szereplo implements Leptetheto {
    protected Aszteroida aszteroida;
    protected Boolean lepett = false;

    public Szereplo() {
        Log.call();
        Jatek.leptethetok.add(this); // Szerepl� l�trej�ttekor a l�ptethet�k list�ja is b�v�l
    }

    /**
     * Az aszteroida param�ter�l kapott sorsz�m� szomsz�dj�ra utazik �s a jelenlegir�l t�rli mag�t
     * 
     * @param sorszam Az aszteroida azonos�t�ja
     */
    public void Mozgas(Integer sorszam) {
        Log.call();
        Hely uj = aszteroida.getSzomszed(sorszam);
        aszteroida.torolSzereplo(this);
        uj.Utazas(this);
    }

    /**
     * Hal�lakor h�v�dik meg, t�rli az aszteroid�r�l �s a l�ptethet�k k�z�l
     */
    public void Meghal() {
        Log.call();
        aszteroida.torolSzereplo(this);
        Jatek.torolLeptetheto(this);
    }

    /**
     * Amikor napvihar �ri a szerepl�t, meghal
     */
    public void Napvihar() {
        Log.call();
        Meghal();
    }

    /**
     * Robban�skor h�v�dik meg, a lesz�rmazottak val�s�tj�k meg
     */
    public void Robbanas() {
        Log.call();
        Meghal();
        // TODO a lesz�rmazottak val�s�tj�k meg
    }

    /**
     * Telepes lesz�rmazott visszaadja a szerepl� nyersanyagjait
     * 
     * @return ArrayList<Nyersanyag> a nyersanyagok list�ja, egy�bk�nt {@code null}
     */
    public ArrayList<Nyersanyag> getNyersanyagok() {
        Log.call();
        return null; // defaultan null-t ad vissza Robot miatt, a lesz�rmazttak fel�l�rj�k
    }

    /**
     * Ezt h�vja emg a k�rt levezet� rendszer
     */
    public void Lepes() {
        Log.call();
    }

    /**
     * Be�ll�tja a param�ter�l kapott aszteroid�t jelenleginek
     * 
     * @param a A be�ll�tand� aszteroida
     */
    public void beallitAszteroida(Aszteroida a) {
        Log.call();
        aszteroida = a;
        a.hozzaadSzereplo(this);
    }

    public Aszteroida getAszteroida() {
        return aszteroida;
    }
    
    @Override
	public Boolean lepette() {
		if(lepett) {
			return true;
		}else {
			lepett = true;
			return false;
		}
	}
    @Override
	public void resetLepett() {
		lepett = false;
	}
}
