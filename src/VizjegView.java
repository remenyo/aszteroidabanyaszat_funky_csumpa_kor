package src;

import java.awt.Color;
import java.awt.Graphics;

public class VizjegView extends JatekView{
	static int x = 350;
	static int y = 250;
	static int oldal = 100;
	
	public void Draw(Graphics g, Integer sorszam) {
		/*int x,y,oldal;
		x=0;
		y=0;
		oldal=0;*/
		Color vilagoskek = new Color(135,206,250);
		g.setColor(vilagoskek);
		g.fillOval(x, y, oldal, oldal);
	}
}
