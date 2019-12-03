
public class Halikarnassos extends Wonder 	
{
	private String name, resource;
	public Halikarnassos()
	{
		super();
		name = "Halikarnassos";
		resource = "loom";
	}
	
	public String getResWonder(int stage)
	{
		if(stage == 1)
			return "clay clay";
		else if(stage == 2)
			return "ore ore ore";
		else
			return "loom loom";
	}
	
	public String getPhase2()
	{
		return "at the end of the round look through discard pile and build a card for free";
	}
}