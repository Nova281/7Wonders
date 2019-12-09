import java.util.ArrayList;

public class Alexandria extends Wonder {
	private String name, resource;

	public Alexandria() {
		super("Alexandria", "glass");
		name = "Alexandria";
		resource = "glass";
	}

	public String getResWonder(int stage) {
		if (stage == 1)
			return "clay clay";
		else if (stage == 2)
			return "wood wood wood";
		else
			return "clay clay clay clay";

	}
<<<<<<< HEAD

	public void runPhase2(Player p) {

=======
	
	public ArrayList<String> runPhase2()
	{
		
>>>>>>> af68fc6b0cde902e35ea4e181618de5fe2943f25
		ArrayList<String> list = new ArrayList<String>();
		list.add("stone");
		list.add("clay");
		list.add("wood");
		list.add("ore");
		return list;
	}

	public String getName() {
		return name;
	}
}
