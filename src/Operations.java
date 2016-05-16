public interface Operations {

	public void execute(Database db);

}

class AddToDatabase implements Operations {
	public String key;
	public Object val;

	AddToDatabase(String k, Object v) {
		key = k;
		val = v;

	}

	public void execute(Database db) {

		db.put(key, val);
	}
}

class GetFromDatabase implements Operations {
	public String key;

	GetFromDatabase(String k) {
		key = k;
	}

	public void execute(Database db) {
		System.out.println(db.get(key).toString());

	}
}

class RemoveFromDB implements Operations {
	String key;

	RemoveFromDB(String k) {
		key = k;
	}

	@Override
	public void execute(Database db) {

		Object x = db.remove(key);
	}

}