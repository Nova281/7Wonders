public class Alexandria extends Wonder	
{
	private String name, resource;
	public Alexandria()
	{
		super();
		super.setName("Alexandria");
		super.setRes("glass");
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
		return "gain resource(Stone, Clay, Wood, Ore) of your choice every turn(cannot be sold through commerce)";
	}
	public String getPhase3()
	{
		return "7 victory points";
	}
	
	
	
	
	
	
	
	
	}
	
