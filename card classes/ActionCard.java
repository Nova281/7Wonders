import java.util.ArrayList;

public class ActionCard extends Card{
	private String effect;
	
	public ActionCard(String name,String color,String age,String cost,String chain, String freeCard)
	{
		super(name,color,age,cost,chain,freeCard);
		setEffect();
	}
	public ActionCard(String inp)
	{
		super(inp);
		setEffect();
	}
	private void setEffect()
	{
		if(getAge() == 1)
		{
			if(getName().equals("East Trading Post"))
				effect = "right 1";
			else if(getName().equals("West Trading Post"))
				effect = "left 1";
			else
				effect = "both 1";
		}
		else if(getAge() == 2)
		{
			if(getName().equals("Vineyard"))
				effect = "Brown 1";
			else if(getName().equals("Forum"))
			{
				effect = "loom glass papyrus";
			}
			else if(getName().equals("Caravansery"))
				effect = "clay stone ore wood";
		}
		else if(getAge() == 3 && getColor().equals("gold"))
		{
			if(getName().equals("Haven"))
				effect = "1 brown";
			else if(getName().equals("Lighthouse"))
				effect = "1 gold";
			else if(getName().equals("Arena"))
				effect = "3 1 wonder";
		}
		else
		{
			if(getColor().contentEquals("purple"))
			{
				if(getName().equals("Workers Guild"))
					effect = "1 Brown";
				else if(getName().equals("Craftsmens Guild"))
					effect = "1 Silver";
				else if(getName().equals("Traders Guild"))
					effect = "1 Gold";
				else if(getName().equals("Philosophers Guild"))
					effect = "1 Green";
				else if(getName().equals("Spies Guild"))
					effect = "1 Red";
				else if(getName().equals("Strategists Guild"))
					effect = "1 Loss";
				else if(getName().equals("Shipowners Guild"))
					effect = "1 Brown Purple Silver";
				else if(getName().equals("Scientists Guild"))
					effect = "1 Science";
				else if(getName().equals("Magistrates Guild"))
					effect = "1 Blue";
				else if(getName().equals("Builders Guild"))
					effect = "1 Wonder";
			}
		}
	}
	public String getEffect()
	{
		return effect;
	}
	public int compareTo(Object o) {
		return super.compareTo(o);
	}
}
