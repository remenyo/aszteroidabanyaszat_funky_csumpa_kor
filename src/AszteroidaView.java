package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class AszteroidaView {
	private Aszteroida aszteroida;
	private Integer reteg; // erre sztem nincs szukseg mo
	private ArrayList<JatekView> nezetek = new ArrayList<JatekView>();

	static int x = 250;
	static int y = 150;
	static int oldal = 300;

	public AszteroidaView(Aszteroida a) {
		aszteroida = a;
		UpdateView();
	}

	public void BeallitAszteroida(Aszteroida a) {
		aszteroida = a;
		reteg = a.getReteg();
	}

	public void Draw(Graphics g, Integer sorszam) {
		// int x,y, oldal;
		g.setColor(Color.DARK_GRAY);
		g.drawOval(x, y, oldal, oldal);

		for (JatekView nezet : nezetek) {
			nezet.Draw(g, sorszam);
		}

		if (reteg != 0) {
			// réteg rárajzolása a nyersanyagra
			int x_reteg = 0, y_reteg = 0, oldal_reteg = 0;
			g.setColor(Color.GRAY);
			g.drawOval(x_reteg, y_reteg, oldal_reteg, oldal_reteg);
		}

	}

	public void UpdateView() {
		nezetek = aszteroida.getAllView();
	}
}
