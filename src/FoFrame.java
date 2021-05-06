package src;

import java.awt.BorderLayout;
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
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(infoPanel, BorderLayout.NORTH);
		this.add(rajzPanel, BorderLayout.CENTER);
		this.add(gombPanel, BorderLayout.SOUTH);
	}

	/**
	 * Beállítja a a frame-nek a telepest akineka köre van és
	 * átadja a paneleknek.
	 * @param t Telepes akinek köre van.
	 */
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
		gombPanel.setTelepes(t);
		rajzPanel.setAszteroida(t.getAszteroida());
		infoPanel.setTelepes(t);
		this.repaint();
	}

}
