package src;

import java.awt.Color;
import java.awt.Graphics;

public class UfoView extends JatekView{
	
	static int x = 350;
	static int y = 450;
	static int width = 100;
	static int height = 50;
	
	public void Draw(Graphics g, Integer sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
		
	}
}
