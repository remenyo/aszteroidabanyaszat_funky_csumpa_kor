package src;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private Telepes jelenlegiTelepes;
	private Integer sorsz = 0;
	private String nyers = "";
	private Integer reteg = 0;
	private Integer portal = 0;
	private String napvihar = "";
	private JLabel sorszLabel = new JLabel("Telepes sorszama: " + sorsz);
	private JLabel nyersLabel = new JLabel("Telepes nyersanyagai: " + nyers);
	private JLabel retegLabel = new JLabel("Aszteroida rétege: " + reteg);
	private JLabel portalLabel = new JLabel("Portálkapuk száma: " + portal);
	private JLabel napviharLabel = new JLabel("Napvihar előrejelzés: " + napvihar);
	
	
	
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
		sorsz = jelenlegiTelepes.getSorszam();
		nyers = "";
		if(jelenlegiTelepes.getNyersanyagok().size() == 0) {
			nyers = "Ures";
		}else {
			for(Nyersanyag ny: jelenlegiTelepes.getNyersanyagok()) {
				nyers += ny.getNev();
				nyers += " ";
			}
		}
		
		
		reteg = jelenlegiTelepes.getAszteroida().getReteg();
		portal = jelenlegiTelepes.getPortal().size();
		if(jelenlegiTelepes.getAszteroida().getElorejelzesvan()) {
			napvihar = "Napvihar lesz!";
		}else {
			napvihar = "Nem lesz napvihar!";
		}
		sorszLabel.setText("Telepes sorszama: " + sorsz);
		nyersLabel.setText("Telepes nyersanyagai: " + nyers);
		retegLabel.setText("Aszteroida rétege: " + reteg);
		portalLabel.setText("Portálkapuk száma: " + portal);
		napviharLabel.setText("Napvihar előrejelzés: " + napvihar);
		this.repaint();
	}
}
