
public class Olympia extends Wonder 	
{
	private String name, resource;
	public Olympia()
	{
		super();
		name = "Olympia";
		resource = "wood";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "wood wood";
		else if(stage == 2)
			return "stone stone";
		else
			return "ore ore";
	}
	
	public String getPhase2()
	{
		return "once per age, build structure of choice for free";
	}
	public String getName()
	{
		return name;
	}
}