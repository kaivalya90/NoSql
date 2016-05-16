import static org.junit.Assert.*;

import org.junit.Test;

public class ArraysTest {

	@Test
	public void testLength() {
		Arrays ar=new Arrays();
		ar.put(10);
		ar.put("Yjdd");
		assertEquals(2, ar.length());
	}



	@Test
	public void testToString() {
		Arrays ar=new Arrays();
		ar.put(10);
		ar.put("Yjdd");
		assertEquals("[10,\"Yjdd\"]", ar.toString());
	}

	@Test
	public void testFromString() {
		
		Arrays sr=Arrays.fromString("[10,\"Yjdd\"]");
		assertEquals("[10,\"Yjdd\"]", sr.toString());
		
	}

}
