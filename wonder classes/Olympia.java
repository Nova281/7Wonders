public class Olympia extends Wonder 	
{
	private String name, resource;
	public Olympia()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Olympia";
	}
	public void setResource()
	{
		resource = "Wood";
	}
	public String getName()
	{
		return name;
	}
	public String getResource()
	{
		return resource;
	}
	public boolean getPhaseState(int stage)
	{
		return super.getPhaseState(stage);
	}
	public String getPhase1()
	{	
		 return "3 victory points";
	}
	public String getPhase2()
	{
		return "once per age, build structure of choice for free";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
