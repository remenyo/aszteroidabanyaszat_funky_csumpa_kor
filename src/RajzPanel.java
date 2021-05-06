package src;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RajzPanel extends JPanel {
	private Aszteroida jelenlegiAszteroida;
	
	public RajzPanel() {
		this.setBackground(Color.BLACK);
	}
	
	public void setAszteroida(Aszteroida a) {
		jelenlegiAszteroida = a;
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//if( jelenlegiAszteroida.getView() != null) {
			jelenlegiAszteroida.getAszteroidaView().Draw(g,0); //Sorszam helyere 0 mer a sajátját nem használja	
		//}
		
	}
}
