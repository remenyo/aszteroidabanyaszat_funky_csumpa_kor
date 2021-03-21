package src;

public class Vizjeg extends Nyersanyag {
    private static int amount = 0;

    Vizjeg() {
        super("Vízjég");
        amount++; // ez feltételezi hogy az új objektum el van tárolva és meg van hívva
        // megszűnéskor a robbanás
    }

    @Override
    public void felszinreKerul(Aszteroida a) {
    	new Log("Meghivodott");
        if (a.isNapkozelben()) {
        	a.torolNyersanyag();
            super.ellenorizNyersanyag();
        }
        new Log("Meghivodott");      
    }

    @Override
    public void robbanas() {
        amount--;
        super.robbanas();
    }
    
    public void ellenorizVesztett() {
    	boolean valasz = new Szkeleton().Kerdes("Van eleg nyersanyag a jatek folytatasahoz?\n1-Igen,2-Nem");
    	if(!valasz) {
    		Jatek.getInstance().jatekVegeVesztett();
    	}
    }
    
}
