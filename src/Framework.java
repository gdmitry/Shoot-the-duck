import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

public class Framework extends Observable {
	static final int DUCKS_NUM = 3;
	static final int STEP = 5;

	private ArrayList<Duck> ducks = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();

	public Framework() {
		setUpDucks(0, 0);
		Timer displayTimer = new Timer(100, listener);
		displayTimer.start();
	}

	ActionListener listener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(checkForKilled());
			setUpDucks(STEP, 0);
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

	private void setUpDucks(int x, int y) {
		for (int i = 0; i < DUCKS_NUM; i++) {
			if ((x == 0) && (y == 0)) {
				ducks.add(new Duck(1100 - 50 * i, 600 + 20 * i));
			} else {
				ducks.get(i).changeLocation(x, y);
			}
		}
	}

	private boolean checkForKilled() {
		boolean result = true;
		Point cursor = MouseInfo.getPointerInfo().getLocation();
		for (int i = 0; i < DUCKS_NUM; i++) {
			Duck d = ducks.get(i);
			if (!(d.location.x < cursor.x) && (d.location.y < cursor.y)) {
				return false;
			} else if ((d.location.x+130 > cursor.x) && (d.location.y+80 > cursor.y)) {
				return true;
			}else
				return false;
		}
		return result;
	}
}
