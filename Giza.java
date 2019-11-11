public class Giza extends Wonder 	
{
	private String name, resource;
	public Giza()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Giza";
	}
	public void setResource()
	{
		resource = "Stone";
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
		return "5 victory points";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
