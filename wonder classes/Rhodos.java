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
	
	public int getPhase2()
	{
		return 2;
	}
	public String getName()
	{
		return name;
	}
}