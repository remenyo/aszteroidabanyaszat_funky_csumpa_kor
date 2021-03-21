package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        this.nev = nev;
    }

    abstract public void ellenorizVesztett();
    public void felszinreKerul(Aszteroida a) {
    	
    }

    public void Robbanas() {
    	Log.info("Meghivodott");
        ellenorizVesztett();
    };

    public boolean azonos(Nyersanyag ny) {
    	Log.info("Meghivodott");
        return this.nev.equals(ny.nev);
    }
}
