package src;

import java.util.ArrayList;

//Aszteroid�kat ismer akiken megh�vja a napvihart valamilyen val�sz�n�s�ggel egy k�r el�rejelz�ssel,
//most tesztel�s miatt egyb�l napvihart h�v.
public class Nap implements Leptetheto {
	private ArrayList<Aszteroida> aszteroidak = new ArrayList<Aszteroida>(); //Nap k�r�li aszteroid�kat t�rol� lista
	private boolean elorejelzesvan = false; //Azt jelzi, hogy az el�rejelz�s van-e, ha igaz akkor indul a napvihar ha hamis akkor nem
											//most tesztel�shez nem haszn�ljuk.
	//Ez a f�ggv�ny felel a napvihar megh�v�s��rt a naphoz tartoz� aszteroid�n.
	public void Lepes() {
		Log.call();
		aszteroidak.get(0).Napvihar();
	}

	//Ez a f�ggv�ny t�rli a napt�l az aszteroid�t.
	public void torolAszteroida(Aszteroida a) {
		Log.call();
		aszteroidak.remove(a);
	}
	//A l�p�s ezt h�vn� meg, hogy az el�rejelz�s alapj�n napvihart gener�ljon
	//de most tesztel�shez nem haszn�ljuk.
	private void Napvihar() {
		Log.call();
	}
	
	//A naphoz hozz�adunk egy aszteroida list�t innent�l ezeken az aszteroid�kon h�vhatja meg a nap
	//a napvihar f�ggv�nyt.
	public void hozzaadAszteroidak(ArrayList<Aszteroida> a) {
		Log.call();
		aszteroidak = a;
	}
}
