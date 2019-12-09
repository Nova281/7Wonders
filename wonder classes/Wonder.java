import java.util.TreeMap;

public class Wonder {
	private TreeMap<Integer, Boolean> Phases;
	private String name, resource, effect;

	public Wonder(String name, String resource) {
		TreeMap<Integer, Boolean> Phases = new TreeMap<>();
		Phases.put(1, false);
		Phases.put(2, false);
		Phases.put(3, false);
		this.name = name;
		this.resource = resource;
	}

	public String getName() {
		return name;
	}

	public boolean getPhaseState(int stage) {
		return Phases.get(stage);
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
