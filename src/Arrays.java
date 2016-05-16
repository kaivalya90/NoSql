import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;

public class Arrays implements Serializable {
	private JSONArray arr;

	Arrays() {
		arr = new JSONArray();
	}

	Arrays(String s) {
		try {
			arr = new JSONArray(s);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public int length() {
		return arr.length();
	}

	public void put(int x) {
		arr.put(x);
	}

	public void put(String s) {
		arr.put(s);
	}

	public void put(Objects o) {
		arr.put(o);

	}

	public String toString() {
		return arr.toString();

	}

	public static Arrays fromString(String s) {
		Arrays newArrays = new Arrays(s);
		return newArrays;
	}

}