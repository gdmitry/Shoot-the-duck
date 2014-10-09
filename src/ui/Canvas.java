package ui;import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import domain.*;
import managers.PictureManager;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements Observer {	
	private ArrayList<Duck> ducks = new ArrayList<>();
	
	@Override
	public void update(Observable framework, Object arg1) {
		ducks=((Framework) framework).getDucks();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);		
		g2d.drawImage(PictureManager.getBackgroundImage(), 0, 0, null);
		for (Duck duck:ducks) {
			g2d.drawImage(duck.getImage(), duck.getLocation().x, duck.getLocation().y, null);		
		}
	}	
	
}
