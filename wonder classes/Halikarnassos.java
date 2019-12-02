public class Halikarnassos extends Wonder 	
{
	private String name, resource;
	public Halikarnassos()
	{
		super();
		super.setName("Halikarnassos");
		super.setRes("loom");
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
			resReq = "ore ore ore";
		}
		else if(stage == 3)
		{
			resReq = "loom loom";
		}
		return resReq;
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
	
