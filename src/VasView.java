package src;

import java.awt.Color;
import java.awt.Graphics;

public class VasView extends JatekView{
public void Draw(Graphics g, Integer sorszam) {
	int x,y,width,height;
	x=0;
	y=0;
	width=0;
	height=0;
	g.setColor(Color.LIGHT_GRAY);
	g.fillOval(x, y, width, height);
}
}