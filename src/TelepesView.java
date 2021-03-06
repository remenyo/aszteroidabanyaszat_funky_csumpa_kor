package src;

import java.awt.Color;
import java.awt.Graphics;

public class TelepesView extends JatekView{
private Telepes taroltTelepes;

public TelepesView(Telepes t) {
	taroltTelepes = t;
}

/**
 * Telepes kirajzolása
 * @param g: kirajzoló grafikus osztály
 * @param sorszam: aszteroida legkeletibb oldalától számolva ezen szöggel eltolva jeleníti meg az objektumot
 */
public void Draw(Graphics g, double sorszam) {
	g.setColor(Color.WHITE);	
	int ujx=(int) (Math.cos(sorszam)*158.1139+375);
	int ujy= (int)(Math.sin(sorszam)*158.1139+275);
	g.fillRect(ujx, ujy, 50, 50);
	
}
}
