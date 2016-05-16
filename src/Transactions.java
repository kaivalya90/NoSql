import java.util.ArrayList;

import javax.activity.InvalidActivityException;

public class Transactions {
	ArrayList<Operations> revertOperations;
	boolean isDone = false; // true indicates either committed or aborted
	Database db;

	Transactions(Database d) {
		isDone = false;
		db = d;
		revertOperations = new ArrayList<Operations>();// operations to be
														// executed in case of
														// abort
	}

	public void put(String key, Object value) throws InvalidActivityException {

		if (isDone) {
			System.out.println("Throwing exception");
			throw new InvalidActivityException();
		}

		Operations x = new RemoveFromDB(key);
		revertOperations.add(x);
		System.out.println("Adding data");
		db.put(key, value);

	}

	public Object remove(String key) throws InvalidActivityException {
		if (isDone) {
			throw new InvalidActivityException();
		}
		Object removedObj = db.remove(key);
		Operations x = new AddToDatabase(key, removedObj);
		revertOperations.add(x);
		return removedObj;
	}

	public Object get(String key) throws InvalidActivityException {

		if (isDone) {
			throw new InvalidActivityException();
		}
		return db.get(key);
	}

	public void commit() {
		isDone = true;
		revertOperations.clear();
	}

	public void abort() {
		for (Operations o : revertOperations) {
			o.execute(db);
		}
		isDone = true;
		revertOperations.clear();
	}

	public boolean isActive() {
		return !isDone;
	}
}