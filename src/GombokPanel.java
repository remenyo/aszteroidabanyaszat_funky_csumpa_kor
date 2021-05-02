package src;

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
		final class mozgasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		mozgasButton.addActionListener(new mozgasButtonActionListener());
		
		final class furasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		furasButton.addActionListener(new furasButtonActionListener());
		
		final class banyaszatButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		banyaszatButton.addActionListener(new banyaszatButtonActionListener());
		
		final class robotEpitesButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		robotEpitesButton.addActionListener(new robotEpitesButtonActionListener());
		
		final class portalEpitesButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		portalEpitesButton.addActionListener(new portalEpitesButtonActionListener());
		
		final class portalLerakasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		portalLerakasButton.addActionListener(new portalLerakasButtonActionListener());
		
		final class nyersanyagLerakasButtonActionListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		}
		nyersanyagLerakasButton.addActionListener(new nyersanyagLerakasButtonActionListener());
		
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
	}
	
	
	
	
	
}
