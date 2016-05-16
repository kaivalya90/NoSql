
public class Propagate extends Observer 
{
	Cursor cursor;
	Propagate(Cursor c)
	{
		cursor=c;
	}
	@Override
	public void update() {
		
		System.out.println("Value to be propagated :" + cursor.value);
		
	}
	
}