package src;

public class Balazsbalfaszkodasa {
	static void teszt_letrehozRobot(String... argumentumok){
		letrehoz("Robot",argumentumok[0]);
		beallit(argumentumok[0],"aszteroida",argumentumok[1]);
	}
	
	static void teszt_letrehozUfo(String... argumentumok){
		letrehoz("Ufo",argumentumok[0]);
		beallit(argumentumok[0],"aszteroida",argumentumok[1]);
	}
	
	static void teszt_letrehozPortalTelepes(String... argumentumok){
		letrehoz("Portal",argumentumok[0]);
		beallit(argumentumok[0],"birtokos",argumentumok[1]);
	}
	
	static void teszt_visszarakNyersanyag(String... argumentumok){
		hiv(argumentumok[0],"visszarakNyersanyag",argumentumok[1]);
	}
	
	static void teszt_lerakPortal(String... argumentumok){
		hiv(argumentumok[0],"lerakPortal",argumentumok[1]);
	}
	
	static void teszt_epitPortal(String... argumentumok){
		ArrayList<Portal> portalok = (ArrayList<Portal>)hiv(argumentumok[0],"epitPortal",null);
		if(portalok != null) {
			objektumok.put(argumentumok[1], portal.get(0));
			objektumok.put(argumentumok[2], portal.get(1));
		}
	}
	
}