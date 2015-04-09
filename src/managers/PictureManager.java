package managers;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PictureManager {
	final static File duck = new File("resources/images/duck.png");
	final static File background = new File("resources/images/background.jpg");
	private static double ratio = 1;

	private static BufferedImage duck_image = null;
	private static BufferedImage background_image = null;

	static {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();		
		double height = screenSize.getHeight()-50;
		int b_height = PictureManager.getBackgroundImage().getHeight();
		int b_width = PictureManager.getBackgroundImage().getWidth()-50;
		ratio=height/b_height;
		if ((ratio<1)&&(ratio>0)){
			background_image=resizeImage(getBackgroundImage(),(int)(ratio*b_width),(int)height);
		}
	}

	public static BufferedImage getDuckImage() {
		duck_image = readImage(duck_image, duck);
		return duck_image;
	}

	public static BufferedImage getBackgroundImage() {
		background_image = readImage(background_image, background);
		return background_image;
	}

	private static BufferedImage readImage(BufferedImage image, File file) {
		if (image == null) {
			try {
				image = ImageIO.read(file);
				System.out.println(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return image;
	}

	private static BufferedImage resizeImage(final Image image, int width,
			int height) {
		final BufferedImage bufferedImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.Src);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		graphics2D.dispose();
		return bufferedImage;
	}
	
	public static double getRatio() {
		return ratio;
	}
}
