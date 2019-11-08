import java.util.*;

public abstract class Card implements Comparable {
	String name;
	int age;
	ArrayList<String> cost;
	ArrayList<String> chain;
	String color;
	String freeCard;
	
	public Card(String nam,int ag,ArrayList<String> cos,String colr,ArrayList<String> chai, String freeCar)
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
		int num1 = 0;
		int num2 = 0;
		if(color.equals("brown"))
			num1 = 0;
		else if(color.equals("silver"))
			num1 = 1;
		else if(color.equals("green"))
			num1 = 2;
		else if(color.equals("blue"))
			num1 = 3;
		else if(color.equals("red"))
			num1 = 4;
		else if(color.equals("gold"))
			num1 = 5;
		else
			num1 = 6;
		
		if(card.getColor().equals("brown"))
			num2 = 0;
		else if(card.getColor().equals("silver"))
			num2 = 1;
		else if(card.getColor().equals("green"))
			num2 = 2;
		else if(card.getColor().equals("blue"))
			num2 = 3;
		else if(card.getColor().equals("red"))
			num2 = 4;
		else if(card.getColor().equals("gold"))
			num2 = 5;
		else
			num2 = 6;
		
		if(num1 < num2)
			return -1;
		else if(num1 > num2)
			return 1;
		else
			return 0;
	}
}
