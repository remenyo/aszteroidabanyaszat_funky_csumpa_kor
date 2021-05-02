package src;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class FoFrame extends JFrame {
	private Telepes jelenlegiTelepes;

	private GombokPanel gombPanel;
	private RajzPanel rajzPanel;
	private InfoPanel infoPanel;

	public FoFrame(GombokPanel gp, RajzPanel rp, InfoPanel ip) {
		super();
		gombPanel = gp;
		rajzPanel = rp;
		infoPanel = ip;
		this.setSize(800, 600);
		
		setLayout(new GridLayout(3, 1));
		this.add(infoPanel);
		this.add(rajzPanel);
		this.add(gombPanel);
	}

	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
		gombPanel.setTelepes(t);
		rajzPanel.setAszteroida(t.getAszteroida());
		infoPanel.setTelepes(t);
		this.repaint();
	}

}
