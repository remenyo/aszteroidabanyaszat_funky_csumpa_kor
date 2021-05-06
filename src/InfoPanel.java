package src;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPanel extends JPanel {
	private Telepes jelenlegiTelepes;
	private Integer sorsz = 0;
	private String nyers = "";
	private Integer reteg = 0;
	private Integer portal = 0;
	private String napvihar = "";
	private String asztnev = "";
	private JLabel sorszLabel = new JLabel("Telepes sorszama: " + sorsz);
	private JLabel nyersLabel = new JLabel("Telepes nyersanyagai: " + nyers);
	private JLabel retegLabel = new JLabel("Aszteroida rétege: " + reteg);
	private JLabel portalLabel = new JLabel("Portálkapuk száma: " + portal);
	private JLabel napviharLabel = new JLabel("Napvihar előrejelzés: " + napvihar);
	private JLabel asztnevLabel = new JLabel("Aszteroida neve: " + asztnev);

	public InfoPanel() {
		
		this.setLayout(new FlowLayout());
		GridLayout grid = new GridLayout(2,1);
		this.setLayout(grid);
		JPanel felso = new JPanel();
		JPanel also = new JPanel();
		felso.setLayout(new FlowLayout());
		also.setLayout(new FlowLayout());
		felso.add(sorszLabel);
		felso.add(asztnevLabel);
		felso.add(retegLabel);
		felso.add(napviharLabel);
		also.add(nyersLabel);
		also.add(portalLabel);
		this.add(felso);
		this.add(also);

		/*
		this.add(sorszLabel);
		this.add(asztnevLabel);
		this.add(nyersLabel);
		this.add(retegLabel);
		this.add(portalLabel);
		this.add(napviharLabel);
		*/
	}

	public void setTelepes(Telepes t) {
		// TODO aszteroida nevgen
		// TODO uran jelezni hényat élt túl visszarakasnal is
		jelenlegiTelepes = t;
		sorsz = jelenlegiTelepes.getSorszam();
		asztnev = jelenlegiTelepes.getAszteroida().getNev();
		nyers = "";
		if (jelenlegiTelepes.getNyersanyagok().size() == 0) {
			nyers = "Ures";
		} else {
			for (Nyersanyag ny : jelenlegiTelepes.getNyersanyagok()) {
				nyers += ny.getNev();
				nyers += " ";
			}
		}

		reteg = jelenlegiTelepes.getAszteroida().getReteg();
		portal = jelenlegiTelepes.getPortal().size();
		if (jelenlegiTelepes.getAszteroida().getElorejelzesvan()) {
			napvihar = "Napvihar lesz!";
		} else {
			napvihar = "Nem lesz napvihar!";
		}
		sorszLabel.setText("#" + sorsz + " telepes");
		asztnevLabel.setText(asztnev + " felszínén | ");
		nyersLabel.setText("Telepes nyersanyagai: " + nyers);
		retegLabel.setText("Aszteroida rétegei: " + reteg);
		portalLabel.setText(" | Portálkapuk száma: " + portal);
		napviharLabel.setText(" | Napvihar előrejelzés: " + napvihar);
		this.repaint();
	}
}
