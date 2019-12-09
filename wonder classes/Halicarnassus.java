
public class Halicarnassus extends Wonder {
	private String name, resource;

	public Halicarnassus() {
		super("Halicarnassus", "cloth");
		name = "Halicarnassus";
		resource = "cloth";
	}

	public String getResWonder(int stage) {
		if (stage == 1)
			return "clay clay";
		else if (stage == 2)
			return "ore ore ore";
		else
			return "cloth cloth";
	}

	public String getPhase2() {
		return "at the end of the round look through discard pile and build a card for free";
	}

	public String getName() {
		return name;
	}
}