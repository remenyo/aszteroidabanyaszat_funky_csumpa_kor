package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RajzPanel extends JPanel {
	private Aszteroida jelenlegiAszteroida; //Aszteroida amit ki kell rajzolni.
	
	public RajzPanel() {
		this.setBackground(Color.BLACK); //Háttér fekete.
	}
	
	/**
	 * Beállítja jelenlegi aszteroidát ami a jelenlegi telepes aszteroidája amit ki kell rajzolni,
	 * és újrarajzoltatja a képernyőt.
	 * @param a aszteroida ami kirajzolandó
	 */
	public void setAszteroida(Aszteroida a) {
		jelenlegiAszteroida = a;
		this.repaint();
	}
	
	/**
	 * Kirajzolja az aszteroidát.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jelenlegiAszteroida.getAszteroidaView().Draw(g,0); //Sorszam helyere 0 mer a sajátját nem használja			
	}
}
