package src;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class FoFrame extends JFrame {
	private Telepes jelenlegiTelepes;//Telepes akinek a köre van.

	private GombokPanel gombPanel;//Gombok panelja
	private RajzPanel rajzPanel;//Rajzolára való panel
	private InfoPanel infoPanel;//Infó kiírás panel

	
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
