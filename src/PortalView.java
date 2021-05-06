package src;

import java.awt.Color;
import java.awt.Graphics;

public class PortalView extends JatekView{
	
	//mező mérete
	static int height = 50;
	
	/**
	 * 
	 * @param g: kirajzoló
	 * @param sorszam: 
	 */
	public void Draw(Graphics g, double sorszam) {
		Color sotetkek = new Color(0,0,153);
		g.setColor(sotetkek);
		int ujx=(int) (Math.cos(sorszam)*158.1139+375);
		int ujy= (int)(Math.sin(sorszam)*158.1139+275);
		g.fillRect(ujx, ujy, height, height);
	}
}
