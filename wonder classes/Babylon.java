public class Babylon extends Wonder 	
{
	private String name, resource;
	public Babylon()
	{
		super();
		super.setName("Babylon");
		super.setRes("clay");
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
			resReq = "clay clay";
		}
		else if(stage == 2)
		{
			resReq = "wood wood wood";
		}
		else if(stage == 3)
		{
			resReq = "clay clay clay clay";
		}
		return resReq;
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
	
