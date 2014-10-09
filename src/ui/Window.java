package ui;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFrame;
import domain.Framework;
import managers.PictureManager;
import managers.SoundManager;

@SuppressWarnings("serial")
public class Window extends JFrame implements MouseListener {
	Framework framework = new Framework();
	Canvas canvas = new Canvas();

	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Shoot the duck");
		setResizable(false);
		this.setSize(new Dimension(PictureManager.getBackgroundImage().getWidth(), PictureManager.getBackgroundImage().getHeight()));
		setCursor();
		framework.addObserver(canvas);
		add(canvas);		
	}	
	
	private void setCursor() {
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		File file = new File("resources/images/cursor.png");
		String absolutePath = file.getAbsolutePath();
		// Load an image for the cursor
		Image image = toolkit.getImage(absolutePath);
		Point hotSpot = new Point(0, 0);
		// Create the custom cursor
		Cursor cursor = toolkit.createCustomCursor(image, hotSpot, "Pencil");
		// Use the custom cursor
		setCursor(cursor);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		// check if any of ducks get killed
		framework.checkForKilled(new Point(x, y));
		SoundManager.playGun();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
