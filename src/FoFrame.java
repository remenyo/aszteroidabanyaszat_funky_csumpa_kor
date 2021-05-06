package src;

import java.awt.BorderLayout;
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
		this.setSize(850, 700);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(infoPanel, BorderLayout.NORTH);
		this.add(rajzPanel, BorderLayout.CENTER);
		this.add(gombPanel, BorderLayout.SOUTH);
	}

	public void setTelepes(Telepes t) {
		// TODO lehet csak telepes viewjat kene atadni infoPanel-nak !!!!
		jelenlegiTelepes = t;
		gombPanel.setTelepes(t);
		rajzPanel.setAszteroida(t.getAszteroida());
		infoPanel.setTelepes(t);
		this.repaint();
	}

}
