package src;

import java.awt.Color;
import java.awt.Graphics;

public class UranView extends JatekView{

	private Uran uran;
	
	public UranView(Uran u) {
		uran = u;
	}
	
	/**
	 * Urán kirajzolása
	 * @param g: kirajzoló grafikus osztály
	 * @param sorszam: Nincs jelentősége (csak leszármazás miatt van)
	 */
	public void Draw(Graphics g, double sorszam) {
		Color sotetzold = new Color(0,100,0);
		g.setColor(sotetzold);
		g.fillOval(350, 250, 100, 100);
		g.setColor(Color.black);
		g.drawString(uran.getnapFenyerte().toString(), 400, 300);
	}
	
}
