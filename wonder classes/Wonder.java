import java.util.TreeMap;

public class Wonder {
	private TreeMap<Integer, Boolean> Phases;
	private String name, resource, effect;

	public Wonder() {
		TreeMap<Integer, Boolean> Phases = new TreeMap<>();
		Phases.put(1, false);
		Phases.put(2, false);
		Phases.put(3, false);
	}

	public String getName() {
		return name;
	}

	public boolean getPhaseState(int stage) {
		return Phases.get(stage);
	}
	
	public void setPhase(int stage)
	{
		Phases.put(stage, true);
	}
	
	public int getPhase1() {
		return 3;
	}

	public int getPhase3() {
		return 7;
	}

	public String getResource() {
		return resource;
	}

	public String getEffect() {
		return effect;
	}
}
