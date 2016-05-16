import java.util.Hashtable;

public class Memento {
	
	private Hashtable<String, Object> dbState;
	
	Memento()
	{
		dbState=new Hashtable<String, Object>();
	}
	
	public void setState(Hashtable<String, Object> state)
	{
		dbState=state;
	}
	
	public Hashtable<String, Object> getState()
	{
		return dbState;
	}
	
	
	
}
