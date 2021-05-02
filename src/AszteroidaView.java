package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AszteroidaView {
	private Aszteroida aszteroida;
	private Integer reteg; //erre sztem nincs szukseg mo
	private ArrayList<JatekView> nezetek = new ArrayList<JatekView>();
	
	public AszteroidaView(Aszteroida a){
		aszteroida = a;
		UpdateView();
	}
	
	public void BeallitAszteroida(Aszteroida a) {
		aszteroida = a;
		reteg=a.getReteg();
	}
	
	public void Draw(Graphics g, Integer sorszam) {
		int x,y, oldal;
		g.setColor(Color.RED);
		g.drawOval(x,y,oldal, oldal);
		
		for(JatekView nezet : nezetek) {
			nezet.Draw(g, sorszam);
		}

	}
	
	public void UpdateView() {
		nezetek = aszteroida.getAllView();
	}
}
