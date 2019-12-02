public class Alexandria extends Wonder	
{
	private String name, resource;
	public Alexandria()
	{
		setName();
		setResource();
	}
	public void setName()
	{
		name = "Alexandria";
	}
	public void setResource()
	{
		resource = "Glass";
	}
	public String getResWonder(int stage)
	{
		String resReq = "";
		if(stage == 1)
		{
			resReq = "stone stone";
		}
		else if(stage == 2)
		{
			resReq = "ore ore";
		}
		else if(stage == 3)
		{
			resReq = "glass glass";
		}
		return resReq;
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
	public int getPhase1()
	{
		 return super.getPhase1();
	}
	public String getPhase2()
	{
		return "gain resource(Stone, Clay, Wood, Ore) of your choice every turn(cannot be sold through commerce)";
	}
	public int getPhase3()
	{
		return super.getPhase3();
	}

}
	
