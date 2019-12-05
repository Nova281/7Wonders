import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;
import java.util.Set;
import static java.lang.System.*;

public class Player {
	
	private TreeMap<String, ArrayList<Card>> cards;
	private Wonder wonder;
	private int coins;
	private int vp;
	
	private int warPoints;
	private int wins; 
	private int loss;
	
	private TreeMap<String, Integer> resources;
	private ArrayList<ArrayList<String>> choiceRes;
	private int mp; //military points
	private TreeMap<String,Integer> sciences;
	
	private boolean brownLeft = false;
	private boolean brownRight = false;
	private boolean silverBoth = false; //what is this stuff
	
	
	public Player() {
		vp = 0;
		warPoints = 0;
		wins = 0;
		loss = 0;
		coins = 0;
		cards = new TreeMap<String, ArrayList<Card>>();
		String[] colors = {"brown", "silver", "blue", "red", "gold", "green", "purple" };
		for(String k: colors)
			cards.put(k, new ArrayList<>());
		resources = new TreeMap<String,Integer>();
		choiceRes = new ArrayList<>();
		sciences = new TreeMap<String,Integer>();
	}
	//sets war points, takes in age and if won
	public void setWar(int age, boolean win)
	{
		if(win == false) {
			loss++;
			warPoints-=1;
		}
		else if(age == 1)
		{
			warPoints += 1;
			wins++;
		}
		else if(age == 2)
		{
			warPoints += 3;
			wins++;
		}
		else
		{
			warPoints += 5;
			wins++;
		}
	}
	
	public void addCard(Card card)
	{
		String color = card.getColor();
		ArrayList<Card> crd = cards.get(color);
		crd.add(card);
		Collections.sort(crd);
		cards.put(color, crd);
		
		if(color.equals("brown") || color.equals("silver"))
		{
			ResourceCard  c = (ResourceCard) card;
			if(c.isChoice())
				choiceRes.add(c.getResources());
			else
			{
				ArrayList<String> resources = c.getResources();
				for(int i = 0; i < resources.size(); i++){
					String resource = resources.get(i);
					if(this.resources.containsKey(resource))
						this.resources.put(resource, this.resources.get(resource) + 1);
					else
						this.resources.put(resource, 1);
				}
			}
			
		}
		else if(color.equals("red"))
		{
			RedCard c = (RedCard) card;
			mp += c.getMP();
		}
		else if(color.equals("green"))
		{
			GreenCard c = (GreenCard) card;
			String science = c.getScience();
			if(cards.containsKey(science))
				sciences.put(science, (sciences.get(science) + 1));
			else
				sciences.put(science, 1);
		}
		else if(color.equals("blue"))
		{
			BlueCard c = (BlueCard) card;
			vp+=c.getVP();
		}
		//idk about action card
		else if(color.equals("gold")|| color.equals("purple"))
		{
			ActionCard c = (ActionCard)card;
			if(c.getEffect().equals("right 1"))
				brownRight = true;
			else if(c.getEffect().equals("left 1"))
				brownLeft = true;
			else if(c.getEffect().equals("both 1"))
				silverBoth = true;
			else if(c.getEffect().equals("loom glass papyrus"))
			{
				String[] array = c.getEffect().split(" ");
				ArrayList<String> re = new ArrayList<String>();
				for(int i = 0; i < array.length; i++)
				{
					re.add(array[i]);
				}
				choiceRes.add(re);
			}
			else if(c.getEffect().equals("clay stone ore wood"))
			{
				String[] array = c.getEffect().split(" ");
				ArrayList<String> re = new ArrayList<String>();
				for(int i = 0; i < array.length; i++)
				{
					re.add(array[i]);
				}
				choiceRes.add(re);
			}
			else if(c.getEffect().equals("1 brown"))
			{
				int num = cards.get("brown").size();
				coins += num;
				vp += num;
			}
			else if(c.getEffect().equals("1 gold"))
			{
				int num = cards.get("gold").size();
				coins += num;
				vp += num;
			}
			else if(c.getEffect().equals("3 1 wonder"))
			{
				int num = 0;
				if(wonder.getPhaseState(1) == true)
					num++;
				else if(wonder.getPhaseState(2) == true)
					num++;
				else if(wonder.getPhaseState(3) == true)
					num++;
				coins += (3 * num);
				vp += num;
			}
		}
	}
	
	public boolean canBuild(Card card)
	{
		ArrayList<String> list = card.getCost();
		if(check(list) == true)
			return true;
		else
			return false;
	}
	//PLEASE DONT REMOVE THIS METHOD A.K.A Check
	private boolean check(ArrayList<String> list)
	{

		boolean ret = true;
		TreeMap<String, Integer> res = new TreeMap<String, Integer>();
		for(int i = 0; i < list.size(); i++)
		{
			if(res.get(list.get(i)) == null)
			{
				res.put(list.get(i),1);
			}
			else
			{
				int num = res.get(list.get(i));
				res.put(list.get(i), (num+1));
			}
		}
		Set<String> keys = res.keySet();
		for(String key : keys)
		{
			int no = res.get(key) - resources.get(key);
			if(no > 0)
			{
				res.put(key,no);
				ret = false;
			}
			else if(no <= 0 && ret == true)
			{
				ret = true;
			}
		}
		keys = res.keySet();
		ArrayList<String> re = new ArrayList<String>();
		for(String key : keys)
		{
			for(int i = 0; i < res.get(key); i++)
			{
				re.add(key);
			}
		}
		for(ArrayList<String> cho : choiceRes)
		{
			String resour = re.get(0);
			if(cho.contains(resour))
			{
				re.remove(0);
			}
		}
		if(re.size() == 0)
		{
			ret = true;
			return true;
		}
		else
			return false;
	}
	public int getMP()
	{
		return mp;
	}
	public boolean hasCard(Card card)
	{
		String cardName = card.getName();
		Set<String> keys = cards.keySet();
		boolean has = false;
		for(String key : keys)
		{
			ArrayList<Card> cardss = cards.get(key);
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
	
	public int getCoins() { return coins; };
	public void setWonder(Wonder wonder)
	{
		this.wonder = wonder;
	}
	/*public void buildWonder()
	{
		
	}
	private void wonderRes(int stage)
	{
		if(!wonder.getPhaseState(stage))
		{
			
		}
	}*/
	
	public void addChoiceRes(ArrayList<String> list) {
		choiceRes.add(list);
	}
	public void addScience(String s) {
		if(sciences.containsKey(s)) 
			sciences.put(s, sciences.get(s) + 1);
		else
			sciences.put(s, 1);
	}
	
	public String toString() { return cards.toString(); }
	public int getWP() { return warPoints; }
	public Wonder getWonder() { return wonder; }
}
