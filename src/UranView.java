package src;

import java.awt.Color;
import java.awt.Graphics;

public class UranView extends JatekView{
	
	static int x = 350;
	static int y = 250;
	static int oldal = 100;
	private Uran uran;
	
	public UranView(Uran u) {
		uran = u;
	}
	
	
	public void Draw(Graphics g, double sorszam) {
		/*int x,y,width,height;
		x=0;
		y=0;
		width=0;
		height=0;*/
		Color sotetzold = new Color(0,100,0);
		g.setColor(sotetzold);
		g.fillOval(x, y, oldal, oldal);
		g.setColor(Color.black);
		g.drawString(uran.getnapFenyerte().toString(), x+oldal/2, y+oldal/2);
	}
	
}
