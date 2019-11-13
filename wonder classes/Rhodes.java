public class Rhodes extends Wonder 	
{
	private String name, resource;
	public Rhodes()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Rhodes";
	}
	public void setResource()
	{
		resource = "Ore";
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
		return "add 2 shields";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
