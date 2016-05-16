import org.json.JSONArray;

public class NoSql {
	public static void main(String[] args) {
		int k = 4;
		String n = "Name";
		String x1 = "\"vfv\"";
		Arrays js = new Arrays();
		js.put(12);
		js.put("wow");
		JSONArray j1 = new JSONArray();
		String s = "[199,\"w8383w\"]";
		Objects x = new Objects();
		x.put("key1", 142);
		x.put("k2", "cschjj");
		x.put("key3", "csc");
		Database db = new Database();
		// db.recover();
		Object c = new Object();
		c = k;
		double x2 = 2893;

		/*
		 * db.put("weiie", x); db.put("csk","csc"); db.put("csjkck", 12);
		 */
		db.recover();
		 db.put("acsckk", js);
		System.out.println(n.getClass());
		//
		// db.snapshot();

	//	db.put("awa111", 182);

		 db.recover("newfile.txt","dbsnap.txt");
		 
			db.put("awacd", 18222);

	//	 db.recover();
		// db.getAll();
		 db.put("cskeu894", "csc");
		// db.put("Ars21122", 18892);

	/*	 db.put("csjk", "sid");
		 db.snapshot();
		 db.put("dchsj","vsvj");
		 db.put("Ars21183", 18.848);
		 db.snapshot();
*/
		 db.snapshot("newfile.txt","dbsnap.txt");
		 
		db.getAllData();

		// db.snapshot();
		// System.out.println(db.get("taj"));
		// System.out.println("Reading from database "+db.get("arrao1"));

		// System.out.println("Class Name:"+ooo.getClass().getName());

	}
}