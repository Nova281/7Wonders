public class Gizah extends Wonder 	
{
	private String name, resource;
	public Gizah()
	{
		super();
		super.setName("Gizah");
		super.setRes("stone");
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
			resReq = "wood wood wood";
		}
		else if(stage == 3)
		{
			resReq = "stone stone stone stone";
		}
		return resReq;
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
	
