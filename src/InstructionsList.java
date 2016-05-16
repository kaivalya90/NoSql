import java.util.ArrayList;

public class InstructionsList
{
	Database db;
	
	ArrayList<Operations> instructions;
	InstructionsList(Database x)
	{
		instructions=new ArrayList<Operations>();
		db=x;
		
	}
	public void putInstructions(Operations o)
	{
		instructions.add(o);
		
	}
	public void executeInstructions()
	{
		for(int i=0;i<instructions.size();i++)
		{
			instructions.get(i).execute(db);
		
		}
		
		instructions.clear();
	}
	
}