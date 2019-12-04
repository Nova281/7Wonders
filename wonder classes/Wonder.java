import java.util.*;
public class Wonder
{
	private TreeMap<Integer, Boolean> Phases;
	private String name, resource, effect;
	
	public Wonder()
	{
		Phases.put(1,false);
		Phases.put(2,false);
		Phases.put(3,false);
	}
	public String getName()
	{
		return name;
	}
	
	public boolean getPhaseState(int stage) { return Phases.get(stage); }
	
	public int getPhase1()
	{
		return 3;
	}
	
	public int getPhase3()
	{
		return 7;
	}
	public String getResource() {
		return resource;
	}
	public String getEffect() {
		return effect;
	}
}
