public class Rhodos extends Wonder 	
{
	private String name, resource;
	public Rhodos()
	{
		super();
		super.setName("Rhodos");
		super.setRes("ore");
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
			resReq = "clay clay";
		}
		else if(stage == 3)
		{
			resReq = "ore ore ore ore";
		}
		return resReq;
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
	
