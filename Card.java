import java.util.*;

public abstract class Card implements Comparable {
	String name;
	int age;
	ArrayList<String> cost;
	ArrayList<String> chain;
	String color;
	String freeCard;
	
	public Card(String nam,int ag,ArrayList<String> cos,String colr,ArrayList<String> chai,String freeCar)
	{
		name = nam;
		age = ag;
		cost = cos;
		color = colr;
		chain = chai;
		freeCard = freeCar;
	}
	public String getName()
	{
		 return name;
	}
	public int getAge()
	{
		 return age;
	}
	public ArrayList<String> getCost()
	{
		 return cost;
	}
	public ArrayList<String> getChain()
	{
		 return chain;
	}
	public String getColor()
	{
		 return color;
	}
	public String getFree()
	{
		return freeCard;
	}
	public int compareTo(Card card)
	{
		if(card.getColor().compareTo(color) < 0)
		{
			return 1;
		}
		else if(card.getColor().compareTo(color) > 0)
		{
			return -1;
		}
		else 
		{
			return 0;
		}
	}
}
