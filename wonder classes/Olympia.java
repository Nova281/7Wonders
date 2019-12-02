public class Olympia extends Wonder 	
{
	private String name, resource;
	public Olympia()
	{
		super();
		super.setName("Olympia");
		super.setRes("wood");
	}
	public boolean getPhaseState(int stage)
	{
		return super.getPhaseState(stage);
	}
	public String getResWonder(int stage)
	{
		String resReq = "";
		if(stage == 1)
		{
			resReq = "wood wood";
		}
		else if(stage == 2)
		{
			resReq = "stone stone";
		}
		else if(stage == 3)
		{
			resReq = "ore ore";
		}
		return resReq;
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
	
