package src;

import java.awt.Color;
import java.awt.Graphics;

public class TelepesView extends JatekView{
private Telepes taroltTelepes;

static int x = 350;
static int y = 100;
//static int width = 100;
static int height = 50;

public TelepesView(Telepes t) {
	taroltTelepes = t;
}

public void Draw(Graphics g, double sorszam) {
	/*int x,y,width,height;
	x=0;
	y=0;
	width=0;
	height=0;*/
	g.setColor(Color.WHITE);	
	int ujx=(int) (Math.cos(sorszam)*206.1553+150);
	int ujy= (int)(Math.sin(sorszam)*206.1553+100);
	g.fillRect(ujx, ujy, height, height);
	
}
}
