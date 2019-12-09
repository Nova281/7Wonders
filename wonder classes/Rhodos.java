public class Rhodos extends Wonder 	
{
	private String name, resource;
	public Rhodos()
	{
		super();
		name = "Rhodos";
		resource = "ore";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "wood wood";
		else if(stage == 2)
			return "clay clay";
		else
			return "ore ore ore ore";
	}
	
	public String getPhase2()
	{
		return "add 2 shields";
	}
	public String getName()
	{
		return name;
	}
}