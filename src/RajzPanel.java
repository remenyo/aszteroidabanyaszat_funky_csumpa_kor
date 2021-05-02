package src;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RajzPanel extends JPanel {
	private Aszteroida jelenlegiAszteroida;
	
	public RajzPanel() {
		
	}
	
	public void setAszteroida(Aszteroida a) {
		jelenlegiAszteroida = a;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//if( jelenlegiAszteroida.getView() != null) {
			jelenlegiAszteroida.getAszteroidaView().Draw(g,0); //Sorszam helyere 0 mer a sajátját nem használja	
		//}
		
	}
}
