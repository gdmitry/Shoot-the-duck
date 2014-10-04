import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class Window extends JFrame implements MouseListener {
	Framework framework = new Framework();
	Canvas canvas = new Canvas();

	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Shoot the duck");
		setSize(1280, 969);
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
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Clicks!!");		
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
