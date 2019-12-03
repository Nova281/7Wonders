import java.util.ArrayList;

public class Alexandria extends Wonder {
	private String name, resource;
	public Alexandria()
	{
		super();
		name = "Alexandria";
		resource = "glass";
	}

	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "clay clay";
		else if(stage == 2)
			return "wood wood wood";
		else
			return "clay clay clay clay";
		
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
}