import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Duck {	
	protected BufferedImage image=null;	
	protected Point location = new Point();
	
	public Duck(int x,int y) {
		setLocation(x,y);
	}
	
	public void setLocation(int x,int y) {
		location.x=x;
		location.y=y;
	}
	
	public void changeLocation(int x,int y) {
		location.x=location.x-x;
		location.y=location.y-y;
	}
	
	public BufferedImage getImage()  {
		File file = new File("resources/images/duck.png");
		String absolutePath = file.getAbsolutePath();
		try {
			image = ImageIO.read(new File(absolutePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
