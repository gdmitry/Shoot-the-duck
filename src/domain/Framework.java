package domain;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import managers.PictureManager;
import managers.SoundManager;

public class Framework extends Observable {
	static final int DUCKS_NUM_MIN = 1;
	static final int DUCKS_NUM_MAX = 9;
	static final int STEP = 5;

	private static int LACKE_TOP = (int) (900 * PictureManager.getRatio());
	private static int LACKE_BOTTOM = (int) (600 * PictureManager.getRatio()) - 20;
	private static int LACKE_LEFT = 0;
	private static int LACKE_RIGHT = (int) (1280 * PictureManager.getRatio());

	private ArrayList<Duck> ducks = new ArrayList<>();
	private ArrayList<Duck> ducksForMissed = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();
	private int ducksNum = new Random().nextInt(DUCKS_NUM_MAX) + DUCKS_NUM_MIN;
	private int num_of_killed = 0;
	private int num_of_missed = 0;

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
			if (num_of_killed == ducksNum) {
				JOptionPane.showMessageDialog(null,
						"Winner! You killed all ducks");
				System.exit(1);
			}
			if (num_of_missed == ducksNum) {
				JOptionPane.showMessageDialog(null,
						"Looser! You didn't kill any duck");
				System.exit(-1);
			}
			if (num_of_missed + num_of_killed == ducksNum) {
				JOptionPane.showMessageDialog(null, "Good job! You killed "
						+ num_of_killed + " ducks");
				System.exit(0);
			}
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
			Duck duck = new Duck(x, y);
			ducks.add(duck);
			ducksForMissed.add(duck);
		}

	}

	private void moveDucks(int x, int y) {
		for (Duck duck : ducks) {
			duck.changeLocation(x, y);
		}
	}

	public boolean checkForKilled(Point cursor) {
		System.out.println("Cursor: " + cursor);
		ArrayList<Duck> remDucks = new ArrayList<Duck>();
		for (Duck duck : ducks) {
			Point xy = duck.getLocation();
			boolean result = isInTriangle(xy.x + 30, xy.y + 5, xy.x + 38,
					xy.y + 52, xy.x + 132, xy.y + 54, cursor.x, cursor.y);
			if (result)
				remDucks.add(duck);
			System.out.println(result);
		}
		for (Duck duck : remDucks) {
			ducks.remove(duck);
			num_of_killed++;
			SoundManager.playDuck();
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

	public int getNumKilled() {
		return num_of_killed;
	}

	public boolean isAnyDuckVisible() {
		return false;

	}

	public int getNumMissed() {
		ArrayList<Duck> remDucks = new ArrayList<Duck>();

		for (Duck duck : ducksForMissed) {
			if (duck.getLocation().x + 100 < LACKE_LEFT) {
				remDucks.add(duck);
			}
		}

		for (Duck duck : remDucks) {
			ducksForMissed.remove(duck);
			num_of_missed++;
		}

		return num_of_missed;
	}

	public int getNumTotal() {
		return ducksNum;
	}

}
