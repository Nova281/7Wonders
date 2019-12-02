import java.util.ArrayList;

public class Alexandria extends Wonder	
{
	private String name, resource;
	public Alexandria()
	{
		super();
		name = "Alexandria";
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
	public void runPhase2(Player p)
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("stone");
		list.add("clay");
		list.add("wood");
		list.add("ore");
		p.addChoiceRes(list);
	}
	public int getPhase3()
	{
		return super.getPhase3();
	}

}
	
