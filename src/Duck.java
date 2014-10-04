import java.awt.Point;
import java.io.File;

import javax.swing.ImageIcon;

public class Duck {
	File file = new File("/resources/images/duck.png");
	String absolutePath = file.getAbsolutePath();
	final ImageIcon image=new ImageIcon(absolutePath);
	private Point location = new Point();
	
}
