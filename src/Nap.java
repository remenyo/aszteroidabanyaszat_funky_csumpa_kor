import java.util.Random;

public class Nap implements Leptetheto{
	private List<Aszteroida> aszteroidak = new ArrayList<Aszteroida>();
	private boolean elorejelzesvan = false;
	
	public void Lepes() {
		if(!elorejelzesvan) {
			Random r = new Random();
			int low = 0;
			int high = 100;
			int result = r.nextInt(high-low) + low;
			if (result <= 10) {
				elorejelzesvan = true;
			}
		}else {
			for (Aszteroida a : aszteroidak) 
			{ 
			    a.Napvihar();
			}
		}
	}
	
	public void torolAszteroida(Aszteroida a) {
		aszteroidak.remove(a);
	}
	
	private void Napvihar() {
		//hat ez ugytunik nem kell de lepesbe belelehet rakni ha akarjuk
	}
	
	public void hozzaadAszteroidak(List<Aszteroida> a) {
		aszteroidak = a;
	}
}
