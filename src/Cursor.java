import java.io.InvalidClassException;
import java.util.List;

public class Cursor {
	String key;
	Object value;
	private List<Observer> observers;

	public Cursor(String k, Object result) {
		key = k;
		value = result;
	}

	public void addObserver(Observer o) {
		observers.add(o);
	}

	public void notifyAllObservers() {
		for (Observer ob : observers) {
			ob.update();
		}
	}

	public void remove(Observer o) {
		observers.remove(o);
	}

	public Object get() {
		return value;
	}

	public int getInt() throws InvalidClassException {
		if (!(value.getClass().getName().equals("java.lang.Integer"))) {
			throw new InvalidClassException(value.getClass().getName());
		}
		return (int) value;
	}

	public String getString() throws InvalidClassException {
		if (!(value.getClass().getName().equals("java.lang.String"))) {
			throw new InvalidClassException(value.getClass().getName());
		}
		return (String) value;
	}

	public Arrays getArrays() throws InvalidClassException {
		if (!(value.getClass().getName().equals("Arrays"))) {
			throw new InvalidClassException(value.getClass().getName());
		}
		return (Arrays) value;
	}

	public Objects getObjects() throws InvalidClassException {
		if (!(value.getClass().getName().equals("Objects"))) {
			throw new InvalidClassException(value.getClass().getName());
		}
		return (Objects) value;
	}

	public void changeValue(Object newValue) {

		value = newValue;
		notifyAllObservers();

	}

}