package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AszteroidaView extends JatekView {
	private Aszteroida aszteroida;
	private ArrayList<JatekView> nezetek = new ArrayList<JatekView>(); //aszteroidán tartózkodó objektumok nézete
	
	public void BeallitAszteroida(Aszteroida a) {
		aszteroida = a;
	 }
	
	/**
	 * Aszteroida és a rajta lévő elemek kirajzolása
	 * @param g: kirajzoló grafikus osztály
	 * @param sorszam: Nincs jelentősége (csak leszármazás miatt van)
	 */
	public void Draw(Graphics g, double sorszam) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(250,150,300, 300);
		
		int nezetmeret;
		//nezetmeret beállítása, hogy ne rontsa el a körvonalra rajzonáson a szimmetriát
		if(aszteroida.getNyersanyag()==null)
			nezetmeret=nezetek.size();
		else
			nezetmeret=nezetek.size()-1;
		
		for(int i=0; i<nezetmeret; i++) {
			//aszteroidán lévő elemek kirajzolása a körvonalára (kivéve a nyersanyag)
			nezetek.get(i).Draw(g, (double) (i*2*3.14/nezetmeret));
		}
		
		if(aszteroida.getReteg()!=0) {
			//réteg rárajzolása
			g.setColor(Color.GRAY);
			g.fillOval(350,250,100, 100);
		}else {
			if(nezetmeret==nezetek.size()-1)
				//a nyersanyag mindig az utolsó
				nezetek.get(nezetmeret).Draw(g, 0);
			//különben nem rajzol semmit
		}

	}
	
	/**
	 * Nézetek betöltése
	 */
	public void UpdateView() {
		nezetek.clear(); 									
		nezetek = aszteroida.getAllView();
	}
}
