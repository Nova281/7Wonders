import java.util.ArrayList;

public class ActionCard extends Card{
	private String effect;
	
	public ActionCard(String name,int age,ArrayList<String> cost,String color,ArrayList<String> chain, String freeCard, String effect)
	{
		super(name,age,cost,color,chain,freeCard);
		this.effect = effect;
	}
//	private void setEffect()
//	{
//		if(getAge() == 1)
//		{
//			if(getName().equals("East Trading Post"))
//				effect = "right 1";
//			else if(getName().equals("West Trading Post"))
//				effect = "left 1";
//			else
//				effect = "both 1";
//		}
//		else if(getAge() == 2)
//		{
//			if(getName().equals("Vineyard"))
//				effect = "Brown 1";
//		}
//		else
//		{
//			if(getColor().contentEquals("purple"))
//			{
//				if(getName().equals("Workers Guild"))
//					effect = "1 Brown";
//				else if(getName().equals("Craftsman Guild"))
//					effect = "1 Silver";
//			}
//		}
//	}
}
