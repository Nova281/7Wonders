import java.util.*;
public abstract class Wonder
{
	private TreeMap<Integer, Boolean> Phases;
	
	public Wonder()
	{
		Phases.put(1,false);
		Phases.put(2,false);
		Phases.put(3,false);
	}
	public boolean getPhaseState(int stage)
	{
		return Phases.get(stage);
	}
//	public abstract String getPhase1();
//	public abstract String getPhase2();
//	public abstract String getPhase3();
//	
	
	
	
	
	
	}
