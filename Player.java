import java.util.*;

public class Player {
	private TreeMap<String,TreeSet<Card>> cards;
	private Wonder wonder;
	private int warP = 0;
	private int loss = 0;
	private TreeMap<String,Integer> resources;
	private ArrayList<ResourceCard> choiceRes;
	private int warSheilds = 0;
	private TreeMap<String,Integer> sciences;
	private int wins = 0;
	private boolean brownLeft = false;
	private boolean brownRight = false;
	private boolean silverBoth = false;
	private int coins;
	
	public Player()
	{
		cards = new TreeMap<String,TreeSet<Card>>();
		wonder = new Wonder();
		resources = new TreeMap<String,Integer>();
		sciences = new TreeMap<String,Integer>();
	}
	public void setWar(int age, boolean win)
	{
		if(age == 1)
		{
			if(win == true)
			{
				warP += 1;
				wins++;
			}
			else
			{
				warP -= 1;
				loss++;
			}
		}
		else if(age == 2)
		{
			if(win == true)
			{
				warP += 3;
				wins++;
			}
			else
			{
				warP -= 1;
				loss++;
			}
		}
		else if(age == 3)
		{
			if(win == true)
			{
				warP += 5;
				wins++;
			}
			else
			{
				warP -= 1;
				loss++;
			}
		}
	}
	public void addCard(Card card)
	{
		String color = card.getColor();
		TreeSet<Card> crd = cards.get(color);
		crd.add(card);
		cards.put(color, crd);
		if(color.equals("brown") || color.equals("silver"))
		{
			ResourceCard  c = (ResourceCard) card;
			if(c.isChoice())
			{
				choiceRes.add(c);
			}
			else
			{
				ArrayList<String> resources = c.getResources();
				for(int i = 0; i < resources.size(); i++){
					String resource = resources.get(i);
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource,1);
					}
					else
					{
						int num = this.resources.get(resource);
						this.resources.put(resource,(num+1));
					}
				}
			}
			
		}
		else if(color.equals("red"))
		{
			RedCard c = (RedCard) card;
			warSheilds += c.getMP();
		}
		else if(color.equals("green"))
		{
			GreenCard c = (GreenCard)card;
			String science = c.getScience();
			if(cards.get(science) == null)
			{
				sciences.put(science, 1);
			}
			else
			{
				int num = sciences.get(science);
				sciences.put(science, (num + 1));
			}
		}
		//else if(color.equals("blue"))
	}
	private boolean checkRes(Card card)
	{
		 ArrayList<String> res = card.getCost();
		 TreeMap<String, Integer> resM = new TreeMap<String, Integer>();
		 ArrayList<String> reso = new ArrayList<String>();
		 boolean ret = true;
		 for(int i = 0; i < res.size(); i++)
		 {
			 String re = res.get(i);
			 if(resM.get(re) == null)
			 {
				 resM.put(re,1);
			 }
			 else
			 {
				 int num = resM.get(re);
				 resM.put(re,(num+1));
			 }
		 }
		 Set<String> keys = resM.keySet();
		 for(String key : keys)
		 {
			 int num = resM.get(key);
			 if(resources.get(key) >= num && ret == true)
				 ret = true;
			 else
			 {
				 int no = num - resources.get(key);
				 if(no > 0)
				 {
					 resources.put(key, no);
				     ret = false;
				     for(int i = 0; i < no; i++)
				     {
				    	 reso.add(key);
				     }
				 }
			 }
		 }
		 ArrayList<String> resou = new ArrayList<String>();
		 for(int i = 0; i < choiceRes.size(); i++)
		 {
			 resou.addAll(choiceRes.get(i).getResources());
		 }
		 while(reso.size() > 0)
		 {
			 String word = reso.get(0);
			 if(resou.contains(word))
			 {
				 reso.remove(0);
			 }
			 else
				 break;
		 }
		 if(reso.size() == 0)
			 ret = true;
		 return ret;
	}
	public int getShields()
	{
		return warSheilds;
	}
	public boolean hasCard(Card card)
	{
		String cardName = card.getName();
		Set<String> keys = cards.keySet();
		boolean has = false;
		for(String key : keys)
		{
			TreeSet<Card> cardss = cards.get(key);
			for(Card crd : cardss)
			{
				if(crd.getName().equalsIgnoreCase(cardName))
				{
					has = true;
				}
			}
		}
		return has;
	}
	public void addCoins(int value)
	{
		coins+= value;
	}
}
