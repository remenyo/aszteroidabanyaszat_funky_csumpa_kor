package src;

import java.awt.GridLayout;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class MozgasFrame extends JFrame{
private JComboBox box;
private ArrayList<Hely> szomszedok;
private RajzPanel rajzPanel=new RajzPanel();
public MozgasFrame(ArrayList<Hely> szomszedok1) {
	szomszedok = szomszedok1;
	for (Hely hely : szomszedok) {
		box.addItem(hely);
	}
	setLayout(new GridLayout(2,1));
	JPanel p1 = new JPanel();
	this.add(p1);
	p1.add(box);
	add(rajzPanel);
	box.addItemListener(new ItemListener() {
		public void itemStateChanged(java.awt.event.ItemEvent evt) {
            rajzPanel.setAszteroida((Hely)box.getSelectedItem());
        }
	});
	}





}
