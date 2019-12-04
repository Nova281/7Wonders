public class Gizah extends Wonder 	
{
	private String name, resource;
	public Gizah()
	{
		super();
		name = "Gizah";
		resource = "stone";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "stone stone";
		else if(stage == 2)
			return "wood wood wood";
		else
			return "stone stone stone stone";
	}
	
	public String getPhase2()
	{
		return "5 victory points";
	}	
	public String getName()
	{
		return name;
	}
}