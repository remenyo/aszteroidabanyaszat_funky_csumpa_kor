package src;

import java.util.Scanner;

public class SzkeletonPoPo78910 {
	public void portalkapuEpites() {
		Scanner be = new Scanner(System.in);
		boolean valasz;
		System.out.println("A telepesnél van már portálkapu?");
		valasz = be.nextBoolean();
		
		if(valasz) {
			System.out.println("A telepesnek van elegendõ nyersanyaga az építéshez?");
			
			
			System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");
		} else {
			System.out.println("Nem sikerült portál létrehozni: ");
		}
		
		if(!valasz) {
			
		}
		
		

	}
	
	public void robotEpites() {
		System.out.println("A telepesnek van elegendõ nyersanyaga az építéshez?");
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");

	}
	
	public void nyersanyagVisszahelyezés() {
		System.out.println("A telepesnél van nyersanyag?");
		
		System.out.println("Az aszteroidában van már elhelyezve nyersanyag?");
		
		System.out.println("Az aszteroida napközelben van?");
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");
		
		System.out.println("Maradtak életben játékosok?");

	}
	
	public void portalLehelyezes() {
		System.out.println("A telepesnél van Portal?");
		
		System.out.println("A portál párjának van végpontja?"); //bõvült
		
		System.out.println("Van elegendõ nyersanyag a játék befejezéséhez?");


	}
}
