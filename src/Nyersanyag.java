package src;

abstract public class Nyersanyag {
    private String nev = "Nyersanyag";

    Nyersanyag(String nev) {
        this.nev = nev;
    }

    abstract public void ellenorizVesztett();

    public void felszinreKerul(Aszteroida a) {
    	//ne csináljon semmit
    };

    public void Robbanas() {
        ellenorizVesztett();
    };

    public boolean azonos(Nyersanyag ny) {
        return this.nev.equals(ny.nev);
    }
}
