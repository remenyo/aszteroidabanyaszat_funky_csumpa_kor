package src;

import java.awt.Color;
import java.awt.Graphics;

public class RobotView extends JatekView{
	
	static int x = 200;
	static int y = 250;
	static int width = 50;
	static int height = 100;
	
	public void Draw(Graphics g, Integer sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		
	}
}
