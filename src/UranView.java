package src;

import java.awt.Color;
import java.awt.Graphics;

public class UranView extends JatekView{
	public void Draw(Graphics g, Integer sorszam) {
		int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;
		Color sotetzold = new Color(0,100,0);
		g.setColor(sotetzold);
		g.fillOval(x, y, width, height);
	}
}
