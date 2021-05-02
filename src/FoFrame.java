package src;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FoFrame extends JFrame {
	private Telepes jelenlegiTelepes;

	private GombokPanel gombPanel;
	private RajzPanel rajzPanel;
	private InfoPanel infoPanel;

	public FoFrame(GombokPanel gp, RajzPanel rp, InfoPanel ip) {
		gombPanel = gp;
		rajzPanel = rp;
		infoPanel = ip;
		
		setLayout(new GridLayout(3,1));
		this.add(infoPanel);
		this.add(rajzPanel);
		this.add(gombPanel);
		
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
		gombPanel.setTelepes(t);
		rajzPanel.setTelepes(t);
		infoPanel.setTelepes(t);
		this.repaint();
	}

}
