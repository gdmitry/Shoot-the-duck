package ui;import java.awt.Font;
import java.awt.Graphics;
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
	private Framework framework;
	
	@Override
	public void update(Observable framework, Object arg1) {
		this.framework=(Framework) framework;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);		
		g2d.drawImage(PictureManager.getBackgroundImage(), 0, 0, null);
		if(framework!=null) {
			for (Duck duck:framework.getDucks()) {
				g2d.drawImage(duck.getImage(), duck.getLocation().x, duck.getLocation().y, null);		
			}
			/* setting font to string message */
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 14)); 
			g2d.drawString("killed: "+Integer.toString(framework.getNumKilled()), 10, 30);
			g2d.drawString("missed: "+Integer.toString(framework.getNumMissed()), 100, 30);
			g2d.drawString("total: "+Integer.toString(framework.getNumTotal()), 180, 30);
		}
		
	}	
	
}
