import static org.junit.Assert.*;

import org.junit.Test;

public class TransactionsTest {

	@Test
	public void testPut() {
		Database db = new Database();
		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			p.put("Agra", 5);			
			p.commit();
			assertEquals(5, db.get("Agra"));
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

	@Test
	public void testRemove() {
		Database db = new Database();
		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			p.put("Agra", 5);
			p.remove("Agra");
			p.commit();
			assertNotEquals(5, db.get("Agra"));
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

	@Test
	public void testGet() {
		Database db = new Database();
		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			p.put("Agra", 5);			
			p.commit();
			assertEquals(5, p.get("Agra"));
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

	@Test
	public void testCommit() {
		Database db = new Database();
		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			p.put("Agra", 5);
			p.commit();

			assertEquals("Mahal", db.get("taj"));
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

	@Test
	public void testAbort() {
		Database db = new Database();
		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			p.put("Agra", 5);
			p.abort();

			assertNotEquals("Mahal", db.get("taj"));
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

	@Test
	public void testIsActive() {
		Database db = new Database();

		Transactions p = db.transaction();
		try {
			p.put("taj", "Mahal");
			assertEquals(true, p.isActive());
			p.put("Agra", 5);
			p.abort();

			assertEquals(false, p.isActive());
		} catch (Exception e) {
			System.out.println("Got the exception" + e.toString());
		}
	}

}
