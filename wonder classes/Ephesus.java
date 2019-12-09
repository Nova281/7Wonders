
public class Ephesus extends Wonder 	
{
	private String name, resource;
	public Ephesus()
	{
		super();
		name = "Ephesus";
		resource = "papyrus";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "stone stone";
		else if(stage == 2)
			return "wood wood";
		else
			return "papyrus papyrus";
	}
	public int getPhase2()
	{
		return 9;
	}
	public String getName()
	{
		return name;
	}
}
