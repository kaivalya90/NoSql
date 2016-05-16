public class Monitor extends Observer {
	private Cursor cursor;

	@Override
	public void update() {
		System.out.println("New value of the cursor " + cursor.value);
	}

	Monitor(Cursor c) {
		cursor = c;
	}

}