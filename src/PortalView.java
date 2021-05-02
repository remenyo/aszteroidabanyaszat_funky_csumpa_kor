package src;

import java.awt.Color;
import java.awt.Graphics;

public class PortalView extends JatekView{
	public void Draw(Graphics g, Integer sorszam) {
		int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;
		Color sotetkek = new Color(0,0,153);
		g.setColor(sotetkek);
		g.fillRect(x, y, width, height);
		
	}
}
