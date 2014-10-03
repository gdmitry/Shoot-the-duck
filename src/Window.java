import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class Window extends JFrame implements MouseListener {
	public Window() {
		setTitle("Shoot the duck");
		setSize(500,300);
		setCursor();
	}

	private void setCursor() {
		// Get the default toolkit
		Toolkit toolkit = Toolkit.getDefaultToolkit();

		// Load an image for the cursor
		Image image = toolkit.getImage("C:\\Users\\dgoncharenko\\Documents\\GitHub\\Shoot the duck\\resources\\images\\cursor.png");
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
