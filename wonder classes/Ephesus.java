
public class Ephesus extends Wonder 	
{
	private String name, resource;
	public Ephesus()
	{
		super();
		name = "Ephesus";
		resource = "papyrus";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "stone stone";
		else if(stage == 2)
			return "wood wood";
		else
			return "papyrus papyrus";
	}
	public String getPhase2()
	{
		return "immediately take 9 coins from bank";
	}
}