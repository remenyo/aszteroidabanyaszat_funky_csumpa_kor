package src;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GombokPanel extends JPanel {
	private Telepes jelenlegiTelepes;
	private JButton mozgasButton = new JButton("Mozgas");
	private JButton furasButton = new JButton("Furas");
	private JButton banyaszatButton = new JButton("Banyaszas");
	private JButton robotEpitesButton = new JButton("Robot epites");
	private JButton portalEpitesButton = new JButton("Portal epites");
	private JButton portalLerakasButton = new JButton("Portal lerakas");
	private JButton nyersanyagLerakasButton = new JButton("Nyersanyag lerakas");
	
	public GombokPanel() {
		
		this.setLayout(new FlowLayout());
		this.add(mozgasButton);
		this.add(furasButton);
		this.add(banyaszatButton);
		this.add(robotEpitesButton);
		this.add(portalEpitesButton);
		this.add(portalLerakasButton);
		this.add(nyersanyagLerakasButton);
				
		
		final class mozgasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO uj menu mozgashoz			
				//jelenlegiTelepes.Mozgas(sorszam);
			}
		}
		mozgasButton.addActionListener(new mozgasButtonActionListener());
		
		final class furasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.Furas();
			}
		}
		furasButton.addActionListener(new furasButtonActionListener());
		
		final class banyaszatButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.Banyaszat();
			}
		}
		banyaszatButton.addActionListener(new banyaszatButtonActionListener());
		
		final class robotEpitesButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.epitRobot();
			}
		}
		robotEpitesButton.addActionListener(new robotEpitesButtonActionListener());
		
		final class portalEpitesButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.epitPortal();
			}
		}
		portalEpitesButton.addActionListener(new portalEpitesButtonActionListener());
		
		final class portalLerakasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.lerakPortal(jelenlegiTelepes.getPortal().get(0));
			}
		}
		portalLerakasButton.addActionListener(new portalLerakasButtonActionListener());
		
		final class nyersanyagLerakasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO vissarakas menu				
				//jelenlegiTelepes.visszarakNyersanyag(ny);
			}
		}
		nyersanyagLerakasButton.addActionListener(new nyersanyagLerakasButtonActionListener());		
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
	}
		
}
