package src;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private Telepes jelenlegiTelepes;
	private JLabel sorszLabel = new JLabel("Telepes sorszama: ");
	private JLabel nyersLabel = new JLabel("Telepes nyersanyagai: ");
	private JLabel retegLabel = new JLabel("Aszteroida rétege: ");
	private JLabel portalLabel = new JLabel("Portálkapuk száma: ");
	private JLabel napviharLabel = new JLabel("Napvihar előrejelzés: ");
	
	
	
	public InfoPanel() {
		this.setLayout(new FlowLayout());
		this.add(sorszLabel);
		this.add(nyersLabel);
		this.add(retegLabel);
		this.add(portalLabel);
		this.add(napviharLabel);
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
		
	}
}
