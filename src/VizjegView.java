package src;

import java.awt.Color;
import java.awt.Graphics;

public class VizjegView extends JatekView{
	
	public void Draw(Graphics g, double sorszam) {
		Color vilagoskek = new Color(135,206,250);
		g.setColor(vilagoskek);
		g.fillOval(350, 250, 100, 100);
	}
}
