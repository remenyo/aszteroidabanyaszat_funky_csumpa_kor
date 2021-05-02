package src;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class MozgasFrame extends JDialog{
private JComboBox box = new JComboBox();
private ArrayList<Hely> szomszedok;
private RajzPanel rajzPanel;
public MozgasFrame(ArrayList<Hely> szomszedok1) {
	super(Jatek.getInstance().getFoFrame(),true);
	szomszedok = szomszedok1;
	rajzPanel = new RajzPanel();
	rajzPanel.setAszteroida(szomszedok1.get(0).szomszedosAszteroida());
	setSize(800,600);
	for (Hely hely : szomszedok1) {
		box.addItem(hely);
	}
	box.setSelectedIndex(0);
	setLayout(new BorderLayout());
	JPanel p1 = new JPanel();
	this.add(p1, BorderLayout.NORTH);
	p1.add(box);
	add(rajzPanel,BorderLayout.CENTER);
	box.addItemListener(new ItemListener() {
		public void itemStateChanged(java.awt.event.ItemEvent evt) {
            rajzPanel.setAszteroida(szomszedok.get(box.getSelectedIndex()).szomszedosAszteroida());
        }
	});
}





}
