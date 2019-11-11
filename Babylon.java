public class Babylon extends Wonder 	
{
	private String name, resource;
	public Babylon()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Babylon";
	}
	public void setResource()
	{
		resource = "Clay";
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
		return "gain extra scientific symbol of their choice at the end of the game";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
