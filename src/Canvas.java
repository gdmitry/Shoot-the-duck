import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Canvas extends JPanel implements Observer {	
	private ArrayList<Duck> ducks = new ArrayList<>();
	
	private BufferedImage showBackground() {		
		File file = new File("resources/images/background.jpg");
		String absolutePath = file.getAbsolutePath();
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(absolutePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
	
	private BufferedImage showDucks() {
		File file = new File("resources/images/duck.png");
		String absolutePath = file.getAbsolutePath();
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File(absolutePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if (ducks.size()!=0) {
		g2d.drawImage(showDucks(), ducks.get(0).location.x,  ducks.get(0).location.y, null);
		g2d.drawImage(showDucks(), ducks.get(1).location.x,  ducks.get(1).location.y, null);
		g2d.drawImage(showDucks(), ducks.get(2).location.x,  ducks.get(2).location.y, null);
		}
	}
	
}
