
public class Halikarnassos extends Wonder {
	public Halikarnassos() {
		super("Halicarnassus", "cloth");
	}

	public String getResWonder(int stage) {
		if (stage == 1)
			return "clay clay";
		else if (stage == 2)
			return "ore ore ore";
		else
			return "cloth cloth";
	}

	public String runPhase2() {
		return "at the end of the round look through discard pile and build a card for free";
	}
}