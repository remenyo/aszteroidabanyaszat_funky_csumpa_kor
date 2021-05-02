package src;

import java.awt.Color;
import java.awt.Graphics;

public class TelepesView extends JatekView{
private Telepes taroltTelepes;

public TelepesView(Telepes t) {
	taroltTelepes = t;
}

public void Draw(Graphics g, Integer sorszam) {
	int x,y,width,height;
	g.setColor(Color.WHITE);
	g.fillRect(x, y, width, height);
	
}
}
