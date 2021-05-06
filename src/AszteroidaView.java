package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AszteroidaView extends JatekView {
	private Aszteroida aszteroida;
	//private int reteg; 															//erre sztem nincs szukseg mo
	private ArrayList<JatekView> nezetek = new ArrayList<JatekView>();
	
	static int x = 250;
	static int y = 150;
	static int oldal = 300;
	
	//réteg elfedi a nyersanyagot
	static int x_reteg = 350;
	static int y_reteg = 250;
	static int oldal_reteg = 100;
	
	/**
	 * public AszteroidaView(Aszteroida a){ 												//kell-e
		BeallitAszteroida(a);
	}
	 
	 */
	
	public void BeallitAszteroida(Aszteroida a) {
		aszteroida = a;
		//reteg = a.getReteg();
	 }
	
	public void Draw(Graphics g, double sorszam) {
		//int x,y, oldal;	
		g.setColor(Color.DARK_GRAY);
		g.fillOval(x,y,oldal, oldal);
		
		int nezetmeret;
		if(aszteroida.getNyersanyag()==null)
			nezetmeret=nezetek.size();
		else
			nezetmeret=nezetek.size()-1;
		
		for(int i=0; i<nezetmeret; i++) {
			nezetek.get(i).Draw(g, (double) (i*2*3.14/nezetmeret));
		}
			
		
		
		if(aszteroida.getReteg()!=0) {
			//réteg rárajzolása a nyersanyagra
			//int x_reteg, y_reteg, oldal_reteg;
			g.setColor(Color.GRAY);
			g.fillOval(x_reteg,y_reteg,oldal_reteg, oldal_reteg);
		}else {
			nezetek.get(nezetek.size()-1).Draw(g, 0);
		}

	}
	
	public void UpdateView() {
		nezetek.clear(); 													//lehet érdemesebb lenne tárolni, hogy változott e a legutolsó rajzolás óta
		nezetek = aszteroida.getAllView();
	}
}
