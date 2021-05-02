package src;

import java.awt.Color;
import java.awt.Graphics;

public class VizjegView extends JatekView{
	public void Draw(Graphics g, Integer sorszam) {
		int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;
		Color vilagoskek = new Color(135,206,250);
		g.setColor(vilagoskek);
		g.fillOval(x, y, width, height);
	}
}
