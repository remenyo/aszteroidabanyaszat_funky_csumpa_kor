package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        Log.ctor();
        this.nev = nev;
    }
    
    //Ezt minden lesz�rmazottnak k�telez�en meg kell val�s�taniuk.
    abstract public void ellenorizVesztett();
    
    //Ha felsz�re ker�l egy nyersanyag akkor h�v�dik meg. Az�rt �res mert a Sz�n �s a Vassal nem t�rt�nik semmi ilyenkor.
    public void felszinreKerul(Aszteroida a) {

    }
    
    //Ha felrobban egy nyersanyag megn�zz�k nem e fogyott el az �sszes nyersanyag.(F�gv�nyen bel�l cs�kken a sz�ml�l�juk).
    public void Robbanas() {
        Log.call();
        ellenorizVesztett();
    };

    //Igaz ha azonosak (n�v alapj�n). Hamis ha nem. 
    public boolean azonos(Nyersanyag ny) {
        Log.call();
        return this.nev.equals(ny.nev);
    }
}
