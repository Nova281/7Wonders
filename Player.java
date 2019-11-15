import java.util.*;

public class Player {
	private TreeMap<String,TreeSet<Card>> cards;
	private Wonder wonder;
	private int warP = 0;
	private int loss = 0;
	private TreeMap<String,Integer> resources;
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
			ArrayList<String> resources = c.getResources();
			for(int i = 0; i < resources.size(); i++){
				String resource = resources.get(i);
				if(resource.equals("wood"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("clay"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("ore"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("stone"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("glass"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("papyrus"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
				else if(resource.equals("loom"))
				{
					if(this.resources.get(resource) == null)
					{
						this.resources.put(resource, 1);
					}
					else
					{
						int num = this.resources.get(resource) + 1;
						this.resources.put(resource, num);
					}
				}
			}
		}
		else if(color.equals("red"))
		{
			RedCard c = (RedCard) card;
			warSheilds += c.getMP();
		}
		else if()
	}
}
