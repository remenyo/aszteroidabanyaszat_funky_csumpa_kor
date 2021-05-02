package src;

import java.awt.Color;
import java.awt.Graphics;

public class SzenView {
	
	static int x = 350;
	static int y = 250;
	static int oldal = 100;
	
	public void Draw(Graphics g, Integer sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		g.setColor(Color.BLACK);
		g.fillOval(x, y, oldal, oldal);
	}
}
