package src;

public class Uran extends Nyersanyag {
    private static Integer osszesUran = 0;
    private Integer napfenyErte;

    // L�trej�ttekor n�veli az �sszes ur�n sz�mot eggyel
    Uran() {
        super("Ur�n");
        Log.ctor();
        osszesUran++;
        napfenyErte = 0;
    }


    /**
     * Felsz�nre ker�ltekor kider�ti, hogy napk�zelben van-e, ha igen robban
     * 
     * @param a aszteroida, amiben a nyersanyag van
     */
    @Override
    public void felszinreKerul(Aszteroida a) {
        Log.call();
        if (a.isNapkozelben()) {
            napfenyErte++;
            if(napfenyErte == 3) {
            	a.Robbanas();
            }
        }
    }

    /**
     * Amikor megsemmis�l a nyersanyag cs�kkenti az �sszes sz�m�t eggyel, majd kider�ti van-e m�g
     * el�g, ha nincs vesztett
     */
    @Override
    public void ellenorizVesztett() {
        Log.call();
        osszesUran--;
        if (osszesUran < 3) {
            Jatek.jatekVegeVesztett();
        }
    }
    
    public String toString() {
    	return osszesUran.toString()+":"+napfenyErte.toString();
    }

}
