import java.util.ArrayList;

public abstract class Card implements Comparable {
	String name;
	int age;
	ArrayList<String> cost;
	ArrayList<String> chain;
	String color;
	String freeCard;
	
	public Card(String name, int age, ArrayList<String> cost, String color, ArrayList<String> chain, String freeCard)
	{
		this.name = name;
		this.age = age;
		this.cost = cost;
		this.color = color;
		this.chain = chain;
		this.freeCard = freeCard;
	}
	
	public String getName() { return name; }
	public int getAge() { return age; }
	public ArrayList<String> getCost() 	{ return cost; }
	public ArrayList<String> getChain() { return chain; }
	public String getColor() { return color; }
	public String getFree() { return freeCard; }
	
	public int compareTo(Object o)
	{
		Card card = (Card) o;
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
		
		return num1-num2;
	}
}
