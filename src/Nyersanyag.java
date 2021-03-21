package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        Log.ctor();
        this.nev = nev;
    }

    abstract public void ellenorizVesztett();

    public void felszinreKerul(Aszteroida a) {

    }

    public void Robbanas() {
        Log.call();
        ellenorizVesztett();
    };

    public boolean azonos(Nyersanyag ny) {
        Log.call();
        return this.nev.equals(ny.nev);
    }
}
