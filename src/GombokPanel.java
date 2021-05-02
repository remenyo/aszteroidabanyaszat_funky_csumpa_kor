package src;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

		final class mozgasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO A		
				MozgasFrame mozgasFrame = new MozgasFrame(jelenlegiTelepes.getAszteroida().getSzomszedok());
				if(mozgasFrame.valasztott) {
					jelenlegiTelepes.Mozgas(mozgasFrame.sorsz);
				}else {
					JOptionPane.showMessageDialog(Jatek.getInstance().getFoFrame(),"Elveszett ez a köröd :(");
				}
				
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		mozgasButton.addActionListener(new mozgasButtonActionListener());

		final class furasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.Furas();
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		furasButton.addActionListener(new furasButtonActionListener());

		final class banyaszatButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.Banyaszat();
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		banyaszatButton.addActionListener(new banyaszatButtonActionListener());

		final class robotEpitesButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.epitRobot();
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		robotEpitesButton.addActionListener(new robotEpitesButtonActionListener());

		final class portalEpitesButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.epitPortal();
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		portalEpitesButton.addActionListener(new portalEpitesButtonActionListener());

		final class portalLerakasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(jelenlegiTelepes.getPortal().size() != 0) {
					jelenlegiTelepes.lerakPortal(jelenlegiTelepes.getPortal().get(0));
					synchronized (Jatek.lepesKesz) {
						Jatek.lepesKesz.notify();
					}	
				}				
			}
		}
		portalLerakasButton.addActionListener(new portalLerakasButtonActionListener());

		
		
		final class nyersanyagLerakasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer visszarakando = -1;
				JComboBox combobox = new JComboBox();
				for(Nyersanyag ny: jelenlegiTelepes.getNyersanyagok()) {
					combobox.addItem(ny.getNev());
				}
				JOptionPane.showMessageDialog(Jatek.getInstance().getFoFrame(),combobox, "Válassza ki melyiket szeretné visszarakni!",JOptionPane.QUESTION_MESSAGE);
				visszarakando = combobox.getSelectedIndex();	
				if(visszarakando >= 0) {
					jelenlegiTelepes.visszarakNyersanyag(jelenlegiTelepes.getNyersanyagok().get(visszarakando));
				}else {
					JOptionPane.showMessageDialog(Jatek.getInstance().getFoFrame(),"Elveszett ez a köröd :(");
				}
				synchronized (Jatek.lepesKesz) {
					Jatek.lepesKesz.notify();
				}
			}
		}
		nyersanyagLerakasButton.addActionListener(new nyersanyagLerakasButtonActionListener());		
			
	}
	
	GombokPanel getInstance() {
		return this;
	}
	
	public void setTelepes(Telepes t) {
		jelenlegiTelepes = t;
	}

}
