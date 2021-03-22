package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        Log.ctor();
        this.nev = nev;
    }
    
    //Ezt minden leszármazottnak kötelezõen meg kell valósítaniuk.
    abstract public void ellenorizVesztett();
    
    //Ha felszíre kerül egy nyersanyag akkor hívódik meg. Azért üres mert a Szén és a Vassal nem történik semmi ilyenkor.
    public void felszinreKerul(Aszteroida a) {

    }
    
    //Ha felrobban egy nyersanyag megnézzük nem e fogyott el az összes nyersanyag.(Fügvényen belül csökken a számlálójuk).
    public void Robbanas() {
        Log.call();
        ellenorizVesztett();
    };

    //Igaz ha azonosak (név alapján). Hamis ha nem. 
    public boolean azonos(Nyersanyag ny) {
        Log.call();
        return this.nev.equals(ny.nev);
    }
}
