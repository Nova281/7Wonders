public class Ephesus extends Wonder 	
{
	private String name, resource;
	public Ephesus()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Ephesus";
	}
	public void setResource()
	{
		resource = "Papyrus";
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
		return "immediately take 9 coins from bank";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
