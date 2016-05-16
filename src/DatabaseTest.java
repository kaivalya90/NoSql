import static org.junit.Assert.*;

import org.junit.Test;

public class DatabaseTest {

	@Test
	public void testSnapshot() {
		Database db=new Database();
		db.recover("newFile.txt","dbSnapShot.txt");
		db.snapshot("newFile.txt","dbSnapShot.txt");
		db.recover("newFile.txt","dbSnapShot.txt");
		assertEquals("[12,\"wow\"]", db.get("acsckk").toString());
	}

	@Test
	public void testSnapshotStringString() {
		Database db=new Database();
		db.recover();
		db.snapshot();
		db.recover();
		assertEquals("[12,\"wow\"]", db.get("acsckk").toString());
	}

	@Test
	public void testRecover() {
		Database db=new Database();
		db.recover();
		assertEquals(182, db.get("awaww"));
	}

	@Test
	public void testRecoverStringString() {
		Database db=new Database();
		db.recover("newFile.txt","dbsnap.txt");
		assertEquals(18222, db.get("awacd"));
	}

	@Test
	public void testPutStringInt() {
		int x=786;
		Database db=new Database();
		db.put("Try", x);
		assertEquals(786, db.get("Try"));
	}

	@Test
	public void testPutStringObject() {
		Object x=786;
		Database db=new Database();
		db.put("Try", x);
		assertEquals(786, db.get("Try"));
		
	}

	@Test
	public void testPutStringDouble() {
		
		Database db=new Database();
		db.put("Try", 3.43);
		assertEquals(3.43, db.get("Try"));
		
	}

	@Test
	public void testPutStringString() {
		Database db=new Database();
		db.put("Try", "Game");
		assertEquals("Game", db.get("Try"));
	}

	@Test
	public void testPutStringObjects() {
		Objects obj=new Objects("{\"name\":\"Roger\",\"age\":21}");
		Database db=new Database();
		db.put("Test", obj);
		assertEquals("{\"name\":\"Roger\",\"age\":21}", db.get("Test").toString());
	}

	@Test
	public void testPutStringArrays() {
		Arrays obj=new Arrays("[2.3,\"at\",1.684,[1,\"me\",{\"a\":1}],\"bat\"]");
		Database db=new Database();
		db.put("Test", obj);
		assertEquals("[2.3,\"at\",1.684,[1,\"me\",{\"a\":1}],\"bat\"]", db.get("Test").toString());
	}

	@Test
	public void testGet() {
		Arrays obj=new Arrays("[2.3,\"at\",1.684,[1,\"me\",{\"a\":1}],\"bat\"]");
		Database db=new Database();
		db.put("Test", obj);
		assertEquals("Arrays", db.get("Test").getClass().getName());

	}

	@Test
	public void testGetString() {
		Database db=new Database();
		db.put("Test", "Hooo");
		assertEquals("java.lang.String", db.get("Test").getClass().getName());
		assertEquals("Hooo", db.getString("Test"));

		
	}

	@Test(expected=IllegalArgumentException.class)
	public void testGetInt() {
		Database db=new Database();
		db.put("Test", "Hooo");
		db.put("Test1", 18);
		assertEquals("java.lang.String", db.get("Test").getClass().getName());
		db.getInt("Test");
		assertEquals(18, db.get("Test1"));
	}

	@Test
	public void testGetObjects() {
		Objects obj=new Objects("{\"name\":\"Roger\",\"age\":21}");
		Database db=new Database();
		db.put("Test", obj);
		assertEquals("{\"name\":\"Roger\",\"age\":21}", db.get("Test").toString());
	}

	@Test
	public void testGetArrays() {
		Arrays obj=new Arrays("[2.3,\"at\",1.684,[1,\"me\",{\"a\":1}],\"bat\"]");
		Database db=new Database();
		db.put("Test", obj);
		assertEquals("[2.3,\"at\",1.684,[1,\"me\",{\"a\":1}],\"bat\"]", db.get("Test").toString());

	}

	@Test
	public void testRemove() {
		Database db=new Database();
		db.put("Test", "Hooo");
		db.put("Test1", 18);
		db.remove("Test");
		assertEquals(null,db.get("Test"));
	}

	@Test
	public void testGetCursor() {
		Database db=new Database();
		db.recover();
		Cursor c=db.getCursor("csjkck");
		assertNotNull(c);
		
		
	}

}
