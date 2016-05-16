import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Set;
import java.util.NoSuchElementException;

public class Database {
	private Hashtable<String, Object> dbData;
	private Cursor cursor;
	private Memento memento;
	File commandFile;
	final String commandFileName = "commands.txt";
	File dbSnapshot;
	final String dbsnapshotFile = "dbSnapshot.txt";
	Boolean recovery = false;

	Database() {
		dbData = new Hashtable<String, Object>();

		try {
			commandFile = new File("commands.txt");
			if (!commandFile.exists()) {
				commandFile.createNewFile();

			}
			dbSnapshot = new File(dbsnapshotFile);
			if (!dbSnapshot.exists()) {
				dbSnapshot.createNewFile();

			}

		}

		catch (Exception e) {
			System.out.println("Error opening file in write mode " + e.getMessage());
		}

	}

	public void snapshot() {

		try {
			memento = new Memento();
			memento.setState(dbData);
			commandFile = new File(commandFileName);
			PrintWriter pw = new PrintWriter(commandFile);
			pw.write("");
			pw.close();
			pw = new PrintWriter(dbSnapshot);
			pw.write("");
			pw.close();
			FileWriter fo = new FileWriter(dbSnapshot, true);
			BufferedWriter bw = new BufferedWriter(fo);
			writeDataToFile(bw);
			bw.close();
			fo.close();

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	public void snapshot(String commandF, String dbSnap) {

		commandFile = new File(commandF);
		File snapshot = new File(dbSnap);
		memento = new Memento();
		memento.setState(dbData);

		try {

			PrintWriter pw = new PrintWriter(commandFile);
			pw.write("");
			pw.close();
			pw = new PrintWriter(snapshot);
			pw.write("");
			pw.close();
			FileWriter fo = new FileWriter(snapshot, true);
			BufferedWriter bw = new BufferedWriter(fo);
			writeDataToFile(bw);
			bw.close();
			fo.close();

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	private void writeDataToFile(BufferedWriter bw) {

		Set<String> datakeys = dbData.keySet();
		try {

			for (String key : datakeys) {
				bw.write(key + " " + dbData.get(key));
				bw.newLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void recover() {
		recovery = true;// set to disable writing to command while

		try {
			FileReader fr = new FileReader(dbSnapshot);
			BufferedReader br = new BufferedReader(fr);
			recoverData(br);
			br.close();
			PrintWriter pw = new PrintWriter(commandFile);
			br = new BufferedReader(new FileReader(commandFile));
			executeCommands(br);
			pw.write("");
			pw.close();
			br.close();
			fr.close();
		} catch (IOException ex) {

			ex.printStackTrace();
		}

		recovery = false;
	}

	public void recover(String cFile, String snapF) {

		File commandF = new File(cFile);
		File dbSnap = new File(snapF);
		recovery = true;// set to disable writing to command while

		try {
			FileReader fr = new FileReader(dbSnap);
			BufferedReader br = new BufferedReader(fr);
			recoverData(br);
			br.close();

			br = new BufferedReader(new FileReader(commandF));
			executeCommands(br);

			br.close();
			fr.close();
		} catch (IOException ex) {

			ex.printStackTrace();
		}

		recovery = false;

	}

	private void executeCommands(BufferedReader br) {
		String line;
		String key;

		try {
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String[] command = line.split(" ");
				if (command[0].equals("remove")) {
					remove(command[1]);
				} else if (command[0].equals("put")) {
					key = command[1];
					if (command[2].charAt(0) == '{') {
						Objects newObject = new Objects(command[2]);
						put(key, newObject);
					}

					else if (command[2].charAt(0) == '[') {
						Arrays newArray = new Arrays(command[2]);
						put(key, newArray);
					} else if (command[2].charAt(0) == '"') {
						put(key, command[2].replace("\"", ""));
					}

					else if (command[2].contains(".")) {
						put(key, Double.parseDouble(command[2]));
					} else
						put(key, Integer.parseInt(command[2]));

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void recoverData(BufferedReader br) {
		String line;
		if (memento != null) {
			dbData = memento.getState();
		} else
			try {

				while ((line = br.readLine()) != null) {
					String[] data = line.split(" ");
					if (data[1].charAt(0) == '{') {
						Objects newObject = new Objects(data[1]);
						put(data[0], newObject);
					} else if (data[1].charAt(0) == '[') {
						Arrays newArray = new Arrays(data[1]);
						put(data[0], newArray);
					} else if (data[1].charAt(0) == '"') {
						put(data[0], data[1].replace("\"", ""));
					} else if (data[1].contains(".")) {
						put(data[0], Double.parseDouble(data[1]));
					} else
						put(data[0], Integer.parseInt(data[1]));
				}

			} catch (IOException io) {
				io.printStackTrace();
			}

	}

	public void put(String key, int value) {
		if (!recovery) {
			writePutCommand(key, value);
		}
		dbData.put(key, value);

		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(value);
		}

	}

	public void put(String key, Object o) {

		if (o.getClass().getName().equals("java.lang.Integer")) {
			put(key, (int) o);

		} else if (o.getClass().getName().equals("java.lang.String")) {
			put(key, (String) o);

		} else if (o.getClass().getName().equals("Objects")) {
			put(key, (Objects) o);

		}

		else if (o.getClass().getName().equals("Arrays")) {
			put(key, (Arrays) o);

		} else if (o.getClass().getName().equals("java.lang.Double")) {
			put(key, (Double) o);
		}

		else
			System.out.println("Could not add to the database");

		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(o);
		}
	}

	public void put(String key, Double d) {
		if (!recovery) {
			writePutCommand(key, d);
		}
		dbData.put(key, d);

		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(d);
		}

	}

	public void put(String key, String value) {
		if (!recovery) {
			writePutCommand(key, value);
		}
		dbData.put(key, value);

		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(value);
		}

	}

	public void put(String key, Objects o) {
		if (!recovery) {
			writePutCommand(key, o);
		}
		dbData.put(key, o);

		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(o);
		}
	}

	private void writePutCommand(String key, Object o) {
		try {
			FileWriter fw = new FileWriter(commandFileName, true);
			BufferedWriter bw = new BufferedWriter(fw);
			if (o.getClass().getName().equals("java.lang.String"))
				bw.write("put " + key + " \"" + o.toString() + "\"");
			else
				bw.write("put " + key + " " + o.toString());
			bw.newLine();
			bw.flush();
			bw.close();
			fw.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void put(String key, Arrays a) {
		if (!recovery) {
			writePutCommand(key, a);
		}
		dbData.put(key, a);
		if (cursor != null && cursor.key.equals(key)) {
			cursor.changeValue(a);
		}
	}

	public Object get(String key) {
		Object s = new Object();
		s = dbData.get(key);

		return s;
	}

	public String getString(String key) {

		Object result = new Object();
		try {
			result = dbData.get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("java.lang.String")))
			throw new IllegalArgumentException();

		return (String) result;

	}

	public int getInt(String key) {

		Object result = new Object();
		try {
			result = dbData.get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("java.lang.Integer")))
			throw new IllegalArgumentException();

		return (int) result;
	}

	public Objects getObjects(String key) {

		Object result = new Object();
		try {
			result = dbData.get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("Objects")))
			throw new IllegalArgumentException();

		return (Objects) result;

	}

	public Arrays getArrays(String key) {

		Object result = new Object();
		try {
			result = dbData.get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("Arrays")))
			throw new IllegalArgumentException();

		return (Arrays) result;

	}

	// displaying all data values to console
	public void getAllData() {
		Set<String> keys = dbData.keySet();
		for (String key : keys) {
			System.out.println("Value of " + key + " is: " + dbData.get(key).toString());
		}

	}

	public Object remove(String key) {
		if (!recovery) {
			try {
				FileWriter fw = new FileWriter(commandFileName, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("remove " + key);
				bw.flush();
				bw.close();
				fw.close();

			} catch (Exception e) {
				System.out.println("Error writing the file :" + e.getMessage());
			}
		}

		Object removedObj = new Object();
		if (dbData.containsKey(key)) {
			removedObj = dbData.get(key);
			dbData.remove(key);

		}
		return removedObj;

	}

	public Cursor getCursor(String k) {
		Object result = get(k);
		if (result == null)
			throw new IllegalArgumentException();
		cursor = new Cursor(k, result);

		return cursor;
	}

	public Transactions transaction() {
		Transactions x = new Transactions(this);
		return x;
	}

}