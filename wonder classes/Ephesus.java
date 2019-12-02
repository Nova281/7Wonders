public class Ephesus extends Wonder 	
{
	private String name, resource;
	public Ephesus()
	{
		super();
		super.setName("Ephesus");
		super.setRes("papyrus");
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
			resReq = "stone stone";
		}
		else if(stage == 2)
		{
			resReq = "wood wood";
		}
		else if(stage == 3)
		{
			resReq = "papyrus papyrus";
		}
		return resReq;
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
	
