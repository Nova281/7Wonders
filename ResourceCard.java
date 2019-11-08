import java.util.ArrayList;

public class ResourceCard extends Card {
	
	ArrayList<String> resources;
	
	public ResourceCard(String name, int age, ArrayList<String> cost, String color, ArrayList<String> chain, String freeCard) {
		super(name, age, cost, color, chain, freeCard);
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
		else {
			if(name.equals("Lumber Yard")) {
				
			}
			else {
				
			}
		}
	}
	
	public int compareTo(Object o) {
		return super.compareTo(o);
	}
	
	public ArrayList<String> getResources() {
		return resources;
	}
	
	public boolean choiceResource() {
		if(name.equals("Timber Yard") || name.equals("Clay Pit"))
			return true;
		return false;
	}

}
