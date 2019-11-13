public class Halicarnassus extends Wonder 	
{
	private String name, resource;
	public Halicarnassus()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Halicarnassus";
	}
	public void setResource()
	{
		resource = "Loom";
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
		return "at the end of the round look through discard pile and build a card for free";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	