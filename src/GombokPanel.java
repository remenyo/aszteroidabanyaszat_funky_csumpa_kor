package src;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

	/**
	 * A játéknak jelzi, hogy lehet tovább menni a körben.
	 */
	static void mehetunk_tovabb() {
		synchronized (Jatek.lepesKesz) {
			Jatek.lepesKesz.notify();
		}
	}

	/**
	 * Amennyiben a felhasználó hülyeséget csinál, ezt az üzenetet látja.
	 */
	static void bena() {
		Jatek.uzenet("Béna vagy", "Ez a köröd elveszett.");
	}

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
				// TODO A
				MozgasFrame mozgasFrame =
						new MozgasFrame(jelenlegiTelepes.getAszteroida().getSzomszedok());
				if (mozgasFrame.valasztott) {
					jelenlegiTelepes.Mozgas(mozgasFrame.sorsz);
					Jatek.uzenet("Utazás sikeres", "Sikeresen elutaztál");
				} else
					bena();
				mehetunk_tovabb();
			}
		}
		mozgasButton.addActionListener(new mozgasButtonActionListener());

		final class furasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jelenlegiTelepes.Furas())
					Jatek.uzenet("MEG 👏 LETT 👏 FÚRVA", "A fúrás sikeres.");
				else
					bena();
				mehetunk_tovabb();
			}
		}
		furasButton.addActionListener(new furasButtonActionListener());

		final class banyaszatButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				jelenlegiTelepes.Banyaszat();
				mehetunk_tovabb();
			}
		}
		banyaszatButton.addActionListener(new banyaszatButtonActionListener());

		final class robotEpitesButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jelenlegiTelepes.epitRobot() == null)
					bena();
				mehetunk_tovabb();
			}
		}
		robotEpitesButton.addActionListener(new robotEpitesButtonActionListener());

		final class portalEpitesButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jelenlegiTelepes.epitPortal() == null) {
					bena();
				}
				mehetunk_tovabb();
			}
		}
		portalEpitesButton.addActionListener(new portalEpitesButtonActionListener());

		final class portalLerakasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jelenlegiTelepes.getPortal().size() != 0) {
					jelenlegiTelepes.lerakPortal(jelenlegiTelepes.getPortal().get(0));
					Jatek.uzenet("LE 👏 LETT 👏 RAKVA", "Sikeresen lehelyezted a portált.");
				} else
					bena();
				mehetunk_tovabb();
			}
		}
		portalLerakasButton.addActionListener(new portalLerakasButtonActionListener());



		final class nyersanyagLerakasButtonActionListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer visszarakando = -1;
				JComboBox<String> combobox = new JComboBox<String>();
				for (Nyersanyag ny : jelenlegiTelepes.getNyersanyagok()) {
					combobox.addItem(ny.getNev());
				}
				JOptionPane.showMessageDialog(Jatek.getInstance().getFoFrame(), combobox,
						"Válassza ki melyiket szeretné visszarakni!", JOptionPane.QUESTION_MESSAGE);
				visszarakando = combobox.getSelectedIndex();
				if (visszarakando >= 0) {
					jelenlegiTelepes.visszarakNyersanyag(
							jelenlegiTelepes.getNyersanyagok().get(visszarakando));
					Jatek.uzenet("LE 👏 LETT 👏 RAKVA", "Sikeresen lehelyeztél egy nyersanyagot.");
				} else
					bena();
				mehetunk_tovabb();
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
