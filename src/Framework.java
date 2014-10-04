import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Framework extends Observable {
	private ArrayList<Duck> ducks = new ArrayList<>();
	private ArrayList<Observer> observers = new ArrayList<>();

	public Framework() {
		// TODO Auto-generated constructor stub
		Duck d = new Duck();

	}
	public void addObserver(Observer c ){
		observers.add(c);
	}
	
	public void notifyObservers() {
		for (Observer o: observers) {
			o.update(this, null);
		}
	}

}
