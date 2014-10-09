package ui;

import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	JLabel count=new JLabel("Count");
	
	public MenuPanel() {
		setSize(200, 100);
		setOpaque(true);
		add(count);
		 
	}
	

}
