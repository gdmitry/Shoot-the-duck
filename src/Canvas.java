import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Canvas extends JPanel implements Observer {	
	private ArrayList<Duck> ducks = new ArrayList<>();
	private static BufferedImage image = null;
	
	public static BufferedImage showBackground() {		
		if (image==null) {
		File file = new File("resources/images/background.jpg");			
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return image;
	}	
	
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
		g2d.drawImage(showBackground(), 0, 0, null);
		for (Duck duck:ducks) {
			g2d.drawImage(duck.getImage(), duck.location.x, duck.location.y, null);		
		}
	}
	
	public void setBackground(BufferedImage image) {
		this.image=image;
	}
	
}
