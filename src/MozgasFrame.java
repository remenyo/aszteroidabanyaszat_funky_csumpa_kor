package src;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.*;

public class MozgasFrame extends JDialog{
private JComboBox<String> box = new JComboBox<String>();
private ArrayList<Hely> szomszedok;
private RajzPanel rajzPanel;
private Button valasztButton = new Button("Kivalaszt");
public Integer sorsz = 0;
public Boolean valasztott = false;


public MozgasFrame(ArrayList<Hely> szomszedok1) {
	super(Jatek.getInstance().getFoFrame(),true);
	setLayout(new BorderLayout());
	setSize(800,600);
	this.szomszedok = szomszedok1;
	
	for (Hely hely : szomszedok1) {
		if(hely.szomszedosAszteroida()!=null) {
			box.addItem(hely.szomszedosAszteroida().getNev());	
		}		
	}
	box.setSelectedIndex(0);	
	box.addItemListener(new ItemListener() {
		public void itemStateChanged(java.awt.event.ItemEvent evt) {
			sorsz = box.getSelectedIndex();
            rajzPanel.setAszteroida(szomszedok.get(box.getSelectedIndex()).szomszedosAszteroida());        
        }
	});
	
	final class valasztButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			valasztott = true;
			dispose();
		}
	}
	valasztButton.addActionListener(new valasztButtonActionListener());
	
	rajzPanel = new RajzPanel();
	rajzPanel.setAszteroida(szomszedok1.get(0).szomszedosAszteroida());	
	JPanel p1 = new JPanel();
	p1.add(box);
	this.add(p1, BorderLayout.NORTH);	
	this.add(rajzPanel,BorderLayout.CENTER);
	this.add(valasztButton,BorderLayout.SOUTH);
	this.setVisible(true);
}





}
