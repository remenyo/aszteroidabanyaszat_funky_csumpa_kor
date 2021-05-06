package src;

import java.awt.Color;
import java.awt.Graphics;

public class UfoView extends JatekView{
	
	static int height = 50;
	
	public void Draw(Graphics g, double sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		g.setColor(Color.GREEN);
		int ujx=(int) (Math.cos(sorszam)*158.1139+375);
		int ujy= (int)(Math.sin(sorszam)*158.1139+275);
		g.fillRect(ujx, ujy, height, height);
	}
}
