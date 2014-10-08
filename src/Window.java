import java.awt.AlphaComposite;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Window extends JFrame implements MouseListener {
	Framework framework = new Framework();
	Canvas canvas = new Canvas();

	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Shoot the duck");
		setScreen();
		setCursor();
		framework.addObserver(canvas);
		add(canvas);
	}
	public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose(); 
        return bufferedImage;
    }
	
	private void setScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		double height = screenSize.getHeight()-50;
		int b_height = canvas.showBackground().getHeight();
		int b_width = canvas.showBackground().getWidth();
		if (b_height > height) {
			canvas.setBackground(resizeImage(canvas.showBackground(),(int)height*b_width/b_height,(int)height));
		}
		this.setSize(new Dimension(canvas.showBackground().getWidth(), canvas.showBackground().getHeight()));
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
		Sound.play("C:\\Users\\Dima\\Documents\\Github\\Shoot-the-duck\\resources\\sounds\\Gun_Shot.wav");

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
