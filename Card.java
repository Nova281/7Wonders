import java.util.ArrayList;

public abstract class Card implements Comparable {
	
	private int age;
	private ArrayList<String> cost;
	private String name, color, freeCard, chain1, chain2, effect;
	
	public Card(String name, String age, String cost, String color, String chain1, String chain2, String freeCard)
	{
		this.name = name;
		this.age = Integer.parseInt(age);
		this.cost = new ArrayList<>();
		String[] c = cost.split(", ");
		for(String k: c)
			this.cost.add(k);
		this.color = color;
		this.chain1 = chain1;
		this.chain2 = chain2;
		this.freeCard = freeCard;
		if(color.equals("brown"))
			effect = "resource";
	}
	
	public String getName() { return name; }
	public int getAge() { return age; }
	public ArrayList<String> getCost() 	{ return cost; }
	public String getChain1() { return chain1; }
	public String getChain2() { return chain2; }
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
