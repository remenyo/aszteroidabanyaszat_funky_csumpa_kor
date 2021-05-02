package src;

import javax.swing.JFrame;

public class FoFrame extends JFrame {
	private Telepes jelenlegiTelepes;

	private GombokPanel gombPanel;
	private RajzPanel rajzPanel;
	private InfoPanel infoPanel;

	public FoFrame(GombokPanel gp, RajzPanel rp, InfoPanel ip) {
		gombPanel = gp;
		rajzPanel = rp;
		infoPanel = ip;
		
		
		
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
		gombPanel.setTelepes(t);
		rajzPanel.setTelepes(t);
		infoPanel.setTelepes(t);
		this.repaint();
	}

}
