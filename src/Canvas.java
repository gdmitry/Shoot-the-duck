import java.awt.Graphics;
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

public class Canvas extends Observable implements Observer {
	BufferedImage scene = new BufferedImage(300, 200,
			BufferedImage.TYPE_3BYTE_BGR);
	private ArrayList<Observer> observers = new ArrayList<>();

	public Canvas()  {
		try {
			drawBackground();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private BufferedImage drawBackground() throws IOException {		
		File file = new File("resources/images/duck.png");
		String absolutePath = file.getAbsolutePath();
		BufferedImage image = ImageIO.read(new File(absolutePath));
		return image;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		try {
			scene = drawBackground();
			notifyObservers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public JPanel getPanel() {
		JPanel pane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(scene, 0, 0, null);
			}
		};
		return pane;
	}

	public void addObserver(Observer c) {
		observers.add(c);
	}

	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this, null);
		}
	}
}
