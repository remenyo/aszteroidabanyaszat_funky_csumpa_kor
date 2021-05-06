package src;

import java.awt.Color;
import java.awt.Graphics;

public class SzenView extends JatekView{
	
	/**
	 * Szén kirajzolása
	 * @param g: kirajzoló grafikus osztály
	 * @param sorszam: Nincs jelentősége (csak leszármazás miatt van)
	 */
	public void Draw(Graphics g, double sorszam) {
		g.setColor(Color.BLACK);
		g.fillOval(350, 250, 100, 100);
	}
}
