import java.util.NoSuchElementException;
import org.json.JSONException;
import org.json.JSONObject;

public class Objects {
	JSONObject obj;

	Objects() {
		obj = new JSONObject();
	}

	Objects(String s) {
		try {
			obj = new JSONObject(s);
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public void put(String k, Object v) {
		try {
			obj.put(k, v);
		} catch (JSONException e) {

			e.printStackTrace();
		}
	}

	public Object get(String key) {
		Object result = new Object();
		try {

			result = obj.get(key);
		} catch (Exception io) {
			io.printStackTrace();
		}
		if (result == null)
			throw new IllegalArgumentException();
		return result;
	}

	public String toString() {
		return obj.toString();
	}

	public final Objects fromString(String s) {
		Objects obj = new Objects(s);
		return obj;

	}

	public int getInt(String k) {
		Object result = new Object();
		try {
			result = obj.get(k);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("java.lang.Integer")))
			throw new IllegalArgumentException();

		return (int) result;
	}

	public String getString(String key) {
		Object result = new Object();
		try {
			result = obj.get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("java.lang.String")))
			throw new IllegalArgumentException();

		return (String) result;

	}

	public Arrays getArrays(String k) {
		Object result = new Object();
		try {
			result = obj.get(k);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("Arrays")))
			throw new IllegalArgumentException();

		return (Arrays) result;
	}

	public Objects getObjects(String k) {
		Object result = new Object();
		try {
			result = obj.get(k);

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (result == null)
			throw new NoSuchElementException();
		if (!(result.getClass().getName().equals("Objects")))
			throw new IllegalArgumentException();

		return (Objects) result;
	}

	public int length() {
		return obj.length();
	}

	public Object remove(String key) {
		Object o = obj.remove(key);
		return o;
	}
}