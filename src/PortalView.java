package src;

import java.awt.Color;
import java.awt.Graphics;

public class PortalView extends JatekView{
	
	static int height = 50;
	
	public void Draw(Graphics g, double sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		Color sotetkek = new Color(0,0,153);
		g.setColor(sotetkek);
		//g.fillRect((int) (Math.cos(sorszam)*206.1553+350), (int)(Math.sin(sorszam)*206.1553+350), width, width);
		int ujx=(int) (Math.cos(sorszam)*158.1139+375);
		int ujy= (int)(Math.sin(sorszam)*158.1139+275);
		g.fillRect(ujx, ujy, height, height);
	}
}
