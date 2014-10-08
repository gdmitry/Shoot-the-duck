import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.Timer;

public class Framework extends Observable {
	static final int DUCKS_NUM_MIN = 1;
	static final int DUCKS_NUM_MAX = 9;
	static final int STEP = 5;
	static final int LACKE_TOP = 900;
	static final int LACKE_BOTTOM = 600;
	static final int LACKE_LEFT = 0;
	static final int LACKE_RIGHT = 1280;

	private ArrayList<Duck> ducks = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();
	private int ducksNum = new Random().nextInt(DUCKS_NUM_MAX) + DUCKS_NUM_MIN;

	public Framework() {
		setUpDucks();
		Timer displayTimer = new Timer(100, listener);
		displayTimer.start();
		System.out.println("Number of ducks: " + ducksNum);
	}

	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			moveDucks(STEP, 0);
			notifyObservers();
		}
	};

	public void addObserver(Observer c) {
		observers.add(c);
	}

	public void notifyObservers() {
		for (Observer o : observers) {
			o.update(this, null);
		}
	}

	public ArrayList<Duck> getDucks() {
		return ducks;
	}

	private void setUpDucks() {
		for (int i = 0; i < ducksNum; i++) {
			int x = new Random().nextInt(LACKE_RIGHT - LACKE_LEFT - 2
					* Duck.getDuckWidth())
					+ LACKE_LEFT + Duck.getDuckWidth();
			int y = new Random().nextInt(LACKE_TOP - LACKE_BOTTOM - 2
					* Duck.getDuckHeight())
					+ LACKE_BOTTOM + Duck.getDuckHeight();
			System.out.println("X: " + x + " Y: " + y);
			ducks.add(new Duck(x, y));
		}
	}

	private void moveDucks(int x, int y) {
		for (Duck duck : ducks) {
			duck.changeLocation(x, y);
		}
	}

	public boolean checkForKilled(Point cursor) {	
		System.out.println("Cursor: "+cursor);
		ArrayList<Duck> remDucks=new ArrayList<Duck>();
		for (Duck duck : ducks) {
			Point xy=duck.getLocation();
			boolean result=isInTriangle(xy.x+30,xy.y+5,xy.x+38,xy.y+52,xy.x+132,xy.y+54,cursor.x,cursor.y);
			if (result)
			remDucks.add(duck);
			System.out.println(result);
		}
		for (Duck duck:remDucks) {
			ducks.remove(duck);
			Sound.play("C:\\Users\\Dima\\Documents\\Github\\Shoot-the-duck\\resources\\sounds\\Duck Pato.wav");
		}
		return true;
	}

	private boolean isInTriangle(int Ax, int Ay, int Bx, int By, int Cx,
			int Cy, int Px, int Py) {
		boolean result = false;
		Bx = Bx - Ax;
		By = By - Ay;
		Cx = Cx - Ax;
		Cy = Cy - Ay;
		Px = Px - Ax;
		Py = Py - Ay;
		//
		double m = (Px * By - Bx * Py) / (Cx * By - Bx * Cy);
		if ((m >= 0) && (m <= 1)) {
			double l = (Px - m * Cx) / Bx;
			if ((l >= 0) && ((m + 1) <= 1)) {
				result = true;
			}
		}
		return result;
	}
	private void setScreen() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
	}
}
