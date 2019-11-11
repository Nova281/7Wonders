import java.util.ArrayList;

public class ResourceCard extends Card {
	
	private ArrayList<String> resources;
	private boolean isChoice;
	
	public ResourceCard(String name, int age, ArrayList<String> cost, String color, ArrayList<String> chain, String freeCard) {
		super(name, age, cost, color, chain, freeCard);
		isChoice = false;
		assignResource();
	}
	
	private void assignResource() {
		resources = new ArrayList<>();
		if(color.equals("silver")) {
			if(name.equals("Press"))
				resources.add("papyrus");
			else if(name.equals("Glassworks"))
				resources.add("glass");
			else
				resources.add("loom");
		}
		else if(age == 1){
			if(name.equals("Lumber Yard"))
				resources.add("wood");
			else if(name.equals("Stone Pit"))
				resources.add("stone");
			else if(name.equals("Clay Pool"))
				resources.add("clay");
			else if(name.equals("Ore Vein"))
				resources.add("ore");
			else if(name.equals("Clay Pit")) {
				resources.add("clay");
				resources.add("ore");
				isChoice = true;
			}
			else {	//Timber Yard
				resources.add("stone");
				resources.add("wood");
				isChoice = true;
			}
		}
		else {
			if(name.equals("Sawmill")) {
				resources.add("wood");
				resources.add("wood");
			}
			else if(name.equals("Quarry")) {
				resources.add("stone");
				resources.add("stone");
			}
			else if(name.equals("Brickyard")) {
				resources.add("stone");
				resources.add("stone");
			}
			else {	//Foundry
				resources.add("ore");
				resources.add("ore");
			}
		}
	}
	
	public int compareTo(Object o) {
		return super.compareTo(o);
	}
	
	public ArrayList<String> getResources() {
		return resources;
	}
	
	public boolean isChoice() {
		return isChoice;
	}

}
