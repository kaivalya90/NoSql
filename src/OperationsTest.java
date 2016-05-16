import static org.junit.Assert.*;

import org.junit.Test;

public class OperationsTest {

	@Test
	public void testExecute() {
	Database db=new Database();
		AddToDatabase ad=new AddToDatabase("Try", "aasad");
		ad.execute(db);
		assertEquals("aasad", db.get("Try"));
		RemoveFromDB rd=new RemoveFromDB("Try");
		rd.execute(db);
		assertEquals(null, db.get("Try"));		
		
	}

}
