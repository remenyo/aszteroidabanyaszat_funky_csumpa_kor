package src;

import java.awt.Graphics;

import javax.swing.JPanel;

public class RajzPanel extends JPanel {
	private Telepes jelenlegiTelepes;
	
	public RajzPanel() {
		
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		jelenlegiTelepes.getAszteroida().getView().Draw(g,0); //Sorszam helyere 0 mer a sajátját nem használja
	}
}
