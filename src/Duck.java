import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Duck {	
	protected BufferedImage image=null;	
	protected Point location = new Point();
	protected static int duck_height=-1;
	protected static int duck_width=-1;
	
	public Duck(int x,int y) {
		setLocation(x,y);
		getImage();
		duck_height=image.getHeight();
		duck_width=image.getWidth();
	}
	
	public void setLocation(int x,int y) {
		location.x=x;
		location.y=y;
	}
	
	public void changeLocation(int x,int y) {
		location.x=location.x-x;
		location.y=location.y-y;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public BufferedImage getImage()  {
		if (image==null) {
			File file = new File("resources/images/duck.png");
			String absolutePath = file.getAbsolutePath();
			try {
				image = ImageIO.read(new File(absolutePath));
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		return image;
	}
	
	public static int getDuckHeight() {
		return duck_height;
	}
	
	public static int getDuckWidth() {
		return duck_width;
	}
}
