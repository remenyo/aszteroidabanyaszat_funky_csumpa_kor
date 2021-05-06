package src;

import java.awt.Color;
import java.awt.Graphics;

public class VasView extends JatekView{
	
	/**
	 * Vas kirajzolása
	 * @param g: kirajzoló grafikus osztály
	 * @param sorszam: Nincs jelentősége (csak leszármazás miatt van)
	 */
	public void Draw(Graphics g, double sorszam) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillOval(350, 250, 100, 100);
	}
}
