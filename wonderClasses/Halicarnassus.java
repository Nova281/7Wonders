package wonderClasses;

public class Halicarnassus extends Wonder {
	public Halicarnassus() {
		super("Halicarnassus", "loom");
	}

	public String getResWonder(int stage) {
		if (stage == 1)
			return "clay clay";
		else if (stage == 2)
			return "ore ore ore";
		else
			return "loom loom";
	}

	public String getPhase2() {
		return "at the end of the round look through discard pile and build a card for free";
	}
}