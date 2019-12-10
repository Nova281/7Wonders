import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Set;
import static java.lang.System.*;

public class Player {
	
	private LinkedHashMap<String, ArrayList<Card>> cards;
	private Wonder wonder;
	private int coins;
	private int vp;
	private int playerNum;
	
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
	
	
	public Player(int num) {
		playerNum = num;
		vp = 0;
		warPoints = 0;
		wins = 0;
		loss = 0;
		coins = 3;
		cards = new LinkedHashMap<String, ArrayList<Card>>();
		String[] colors = {"brown", "silver", "blue", "red", "gold", "green", "purple" };
		for(String k: colors)
			cards.put(k, new ArrayList<>());
		resources = new TreeMap<String,Integer>();
		choiceRes = new ArrayList<>();
		sciences = new TreeMap<String,Integer>();
	}
	//sets war points, takes in age and if won
	public void wageWar(int age, Player p)
	{
		if(p.getMP() > mp) {
			loss++;
			warPoints-=1;
			p.setWar(age, true);
			return;
		}
		else if(p.getMP() == mp)
			return;
		else if(age == 1)
			warPoints += 1;
		else if(age == 2)
			warPoints += 3;
		else
			warPoints += 5;
		wins++;
		p.setWar(age, false);
	}
	
	private void setWar(int age, boolean win) {
		if(win) {
			wins++;
			if(age == 1)
				warPoints++;
			else if(age == 2)
				warPoints+=3;
			else
				warPoints+=5;
		}
		else {
			loss++;
			warPoints--;
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
				addChoiceRes(re);
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
			else if(c.getEffect().equals("Brown 1"))
			{
				int num = getBrownNum() + p1.getBrownNum() + p2.getBrownNum();
				addCoins(num);
			}
			else if(c.getEffect().equals("1 brown"))
			{
				int num = getBrownNum();
				coins += num;
				vp += num;
			}
			else if(c.getEffect().equals("1 gold"))
			{
				int num = getGoldNum();
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
			else if(c.getName().equals("Workers Guild"))
			{
				int num = p1.getBrownNum() + p2.getBrownNum();
				addVP(num);
			}
			else if(c.getName().equals("Craftsmens Guild"))
			{
				int num = p1.getSilverNum() + p2.getSilverNum();
				addVP(num * 2);
			}
			else if(c.getName().equals("Traders Guild"))
			{
				int num = p1.getGoldNum() + p2.getGoldNum();
				addVP(num);
			}
			else if(c.getName().equals("Philosophers Guild"))
			{
				int num = p1.getGreenNum() + p2.getGreenNum();
				addVP(num);
			}
			else if(c.getName().equals("Spies Guild"))
			{
				int num = p1.getRedNum() + p2.getRedNum();
				addVP(num);
			}
			else if(c.getName().equals("Strategists Guild"))
			{
				int num = p1.getLosses() + p2.getLosses();
				addVP(num);
			}
			else if(c.getName().equals("Shipowners Guild"))
			{
				int num = getPurpleNum() + getBrownNum() + getSilverNum();
				addVP(num);
			}
			else if(c.getName().equals("Magistrates Guild"))
			{
				int num = p1.getBlueNum() + p2.getBlueNum();
				addVP(num);
			}
			else if(c.getName().equals("Builders Guild"))
			{
				int num = 0;
				if(wonder.getPhaseState(1) == true)
					num++;
				else if(wonder.getPhaseState(2) == true)
					num++;
				else if(wonder.getPhaseState(3) == true)
					num++;
				Wonder wonderr = p1.getWonder();
				if(wonderr.getPhaseState(1) == true)
					num++;
				else if(wonderr.getPhaseState(2) == true)
					num++;
				else if(wonderr.getPhaseState(3) == true)
					num++;
				Wonder wonderrr = p2.getWonder();
				if(wonderrr.getPhaseState(1) == true)
					num++;
				else if(wonderrr.getPhaseState(2) == true)
					num++;
				else if(wonderrr.getPhaseState(3) == true)
					num++;
				addVP(num);
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
	public TreeMap<String,Integer> getResources()
	{
		return resources;
	}
	//PLEASE DONT REMOVE THIS METHOD A.K.A Check
	private boolean check(ArrayList<String> cost)
	{
		//check through normal resources
		if(cost.size() == 1 && cost.get(0).equals(" "))
			return true;
		TreeMap<String, Integer> res = new TreeMap<String, Integer>();
		for(String k: resources.keySet())
			res.put(k, resources.get(k));
		for(int i = cost.size()-1; i >= 0; i--) {
			String resource = cost.get(i);
			if(res.containsKey(resource)) {
				int numRes = res.get(cost.get(i))-1;
				cost.remove(i);
				if(numRes == 0)
					res.remove(resource);
				else
					res.put(resource, numRes);
			}
		}
		
		//check through choice resources;
		if(cost.size() > 0 && cost.size() <= choiceRes.size()) {
			ArrayList<String> c = new ArrayList<String>();
			ArrayList<ArrayList<String>> combinations = new ArrayList<>();
			createCombo(c, 0, "");
			String[] a;
			for(String k: c) {
				a = k.split(";");
				ArrayList<String> b = new ArrayList<>();
				for(int i = 1; i < a.length; i++)
					b.add(a[i]);
				combinations.add(b);
			}
			if(combinations.contains(cost))
				return true;
			else {
				for(ArrayList<String> k: combinations) {
					for(int i = cost.size()-1; i >= 0; i--)
						if(k.contains(cost.get(i))) {
							k.remove(i);
						}
					if(k.size() == 0)
						return true;
				}
			}
			return false;
		}
		else
			return true;
	}
	
	//used for check
	private void createCombo(ArrayList<String> combinations, int runs, String current) {
	    if (runs == choiceRes.size()) {
	        combinations.add(current);
	        return;
	    }
	    for (int i = 0; i < choiceRes.get(runs).size(); i++) {
	        createCombo(combinations, runs + 1, current + ";" + choiceRes.get(runs).get(i));
	    }
	}
	
	public ArrayList<ArrayList<String>> getChoiceRes() { return choiceRes; }
	
	public int getMP()
	{
		return mp;
	}
	
	public boolean hasCard(Card card)
	{
		String cardName = card.getName();
		Set<String> keys = cards.keySet();
		boolean has = false;
		for(String key: keys)
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
	public void buildWonder()
	{
		int stage = 0;
		if(wonder.getPhaseState(1) == false)
			stage = 1;
		else if(wonder.getPhaseState(2) == false)
			stage = 2;
		else if(wonder.getPhaseState(3) == false)
			stage = 3;
		else
			return;
		if(wonder.getName().equals("Alexandria"))
		{
			Alexandria alex = (Alexandria)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				if(stage == 2)
				{
					ArrayList<String> choi = alex.runPhase2();
					addChoiceRes(choi);
					wonder.setPhase(stage);
				}
			}
		}
		else if(wonder.getName().equals("Babylon"))
		{
			Babylon alex = (Babylon)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				/*if(stage == 2)
				{
					ArrayList<String> choi = alex.runPhase2();
					addChoiceRes(choi);
					wonder.setPhase(stage);
				}*/
			}
		}
		else if(wonder.getName().equals("Ephesus"))
		{
			Ephesus alex = (Ephesus)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				if(stage == 2)
				{
					int coi = alex.runPhase2();
					addCoins(coi);
					wonder.setPhase(stage);
				}
			}
		}
		else if(wonder.getName().equals("Gizah"))
		{
			Gizah alex = (Gizah)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				if(stage == 2)
				{
					vp += alex.runPhase2();
					wonder.setPhase(stage);
				}
			}
		}
		else if(wonder.getName().equals("Halicarnassus"))
		{
			Halikarnassos alex = (Halikarnassos)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				/*if(stage == 2)
				{
					ArrayList<String> choi = alex.runPhase2();
					addChoiceRes(choi);
					wonder.setPhase(stage);
				}*/
			}
		}
		else if(wonder.getName().equals("Olympia"))
		{
			Olympia alex = (Olympia)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				/*if(stage == 2)
				{
					ArrayList<String> choi = alex.runPhase2();
					addChoiceRes(choi);
					wonder.setPhase(stage);
				}*/
			}
		}
		else if(wonder.getName().equals("Rhodos"))
		{
			Rhodos alex = (Rhodos)wonder;
			String[] array = alex.getResWonder(stage).split(" ");
			ArrayList<String> res = new ArrayList<String>();
			for(int i = 0; i < array.length; i++)
			{
				res.add(array[i]);
			}
			if(check(res) == true)
			{
				runWonder(stage);
				if(stage == 2)
				{
					mp += alex.runPhase2();
					wonder.setPhase(stage);
				}
			}
		}
	}
	private void runWonder(int stage)
	{
		if(stage == 1)
		{
			int num = wonder.getPhase1();
			vp += num;
			wonder.setPhase(stage);
		}
		else if(stage == 3)
		{
			int num = wonder.getPhase3();
			vp += num;
			wonder.setPhase(stage);
		}
	}
	
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
	public boolean trade(Player left, Player right, ArrayList<String> cost)
	{
		LinkedHashMap<String, Integer> rColors = new LinkedHashMap<>();
		rColors.put("brown", 0);
		rColors.put("silver", 0);
		String[] brown = {"wood", "stone", "ore", "clay"};
		ArrayList<String> brownList = (ArrayList<String>) Arrays.asList(brown);
		String[] silver = {"cloth", "papyrus", "glass"};
		ArrayList<String> silverList = (ArrayList<String>) Arrays.asList(silver);
		ActionCard ETP = (new ActionCard("East Trading Post;gold;1; ;Forum; "));
		ActionCard WTP = (new ActionCard("West Trading Post;gold;1; ;Forum; "));
		ActionCard Market = (new ActionCard("Marketplace;gold;1; ;Caravansery; "));
		ResourceCard[] brownCard = {new ResourceCard("Clay Pit;brown;1; ; ; "), //0
				new ResourceCard("Clay Pool;brown;1; ; ; "), //1
				new ResourceCard("Lumber Yard;brown;1; ; ; "), //2
				new ResourceCard("Ore Vein;brown;1; ; ; "), //3
				new ResourceCard("Stone Pit;brown;1; ; ; "), //4
				new ResourceCard("Timber Yard;brown;1; ; ; ")}; //5
		ResourceCard[] silverCard = {new ResourceCard("Glassworks;silver;1; ; ;"), //0
				new ResourceCard("Loom;silver;1; ; ; "), //1
				new ResourceCard("Press;silver;1; ; ; ")}; //2
		
		for(int i = cost.size()-1; i >= 0; i--) {
			String r = cost.get(i);
			if(left.hasResource(r)) {
				cost.remove(r);
				if(brownList.contains(r))
					
			}
			else if(right.hasResource(r)) {
				cost.remove(r);
			}
		}
		Iterator<String> iter = left.getResources().keySet().iterator();
		
		//check through normal resources of left
//		if(cost.size() == 1 && cost.get(0).equals(" "))
//			return true;
//		TreeMap<String, Integer> res = new TreeMap<String, Integer>();
//		for(String k: left.getResources().keySet())
//			res.put(k, left.getResources().get(k));
//		for(int i = cost.size()-1; i >= 0; i--) {
//			String resource = cost.get(i);
//			if(res.containsKey(resource)) {
//				int numRes = res.get(cost.get(i))-1;
//				if(brownList.contains(cost.remove(i)))
//					removedColors.put("brown", removedColors.get("brown") + 1);
//				else
//					removedColors.put("silver", removedColors.get("silver") + 1);
//				if(numRes == 0)
//					res.remove(resource);
//				else
//					res.put(resource, numRes);
//			}
//		}
//		if(cost.size() == 0) {
//			//brown cards
//			if(left.hasCard(WTP)) {
//				left.addCoins(removedColors.get("brown"));
//				coins-=removedColors.get("brown");
//			}
//			else {
//				left.addCoins(leftR*2);
//				coins-=(leftR*2);
//			}
//			return true;
//		}
//		//check through choice resources of left
//		int costSize = cost.size();
//		ArrayList<String> c = new ArrayList<String>();
//		ArrayList<ArrayList<String>> combinations = new ArrayList<>();
//		createCombo(c, 0, "");
//		String[] a;
//		for(String k: c) {
//			a = k.split(";");
//			ArrayList<String> b = new ArrayList<>();
//			for(int i = 1; i < a.length; i++)
//				b.add(a[i]);
//			combinations.add(b);
//		}
//		ArrayList<String> small = new ArrayList<>(cost);
//		for(ArrayList<String> k: combinations) {
//			for(int i = cost.size()-1; i >= 0; i--)
//				if(k.contains(cost.get(i))) {
//					k.remove(i);
//				}
//			if(k.size() == 0) {
//				leftR += costSize;
//				if(left.hasCard(WTP)) {
//					left.addCoins(leftR);
//					coins-=leftR;
//				}
//				else {
//					left.addCoins(leftR*2);
//					coins-=(leftR*2);
//				}
//				return true;
//			}
//			else if(small.size() > cost.size()) {
//				small = cost;
//			}
//		}
//		cost = small;
//		leftR+=(costSize-cost.size());
//		
//		//check through normal resources of right
//		res = new TreeMap<String, Integer>();
//		for(String k: right.getResources().keySet())
//			res.put(k, right.getResources().get(k));
//		for(int i = cost.size()-1; i >= 0; i--) {
//			String resource = cost.get(i);
//			if(res.containsKey(resource)) {
//				int numRes = res.get(cost.get(i))-1;
//				cost.remove(i);
//				rightR++;
//				if(numRes == 0)
//					res.remove(resource);
//				else
//					res.put(resource, numRes);
//			}
//		}
//		if(cost.size() == 0) {
//			if(right.hasCard(ETP)) {
//				right.addCoins(rightR);
//				coins-=rightR;
//			}
//			else {
//				right.addCoins(rightR*2);
//				coins-=(rightR*2);
//			}
//			return true;
//		}
//		//check through choice resources of right
//		costSize = cost.size();
//		if(cost.size() > 0 && cost.size() <= right.getChoiceRes().size()) {
//			c = new ArrayList<String>();
//			combinations = new ArrayList<>();
//			createCombo(c, 0, "");
//			for(String k: c) {
//				a = k.split(";");
//				ArrayList<String> b = new ArrayList<>();
//				for(int i = 1; i < a.length; i++)
//					b.add(a[i]);
//				combinations.add(b);
//			}
//			if(combinations.contains(cost))
//				return true;
//			else {
//				for(ArrayList<String> k: combinations) {
//					for(int i = cost.size()-1; i >= 0; i--)
//						if(k.contains(cost.get(i))) {
//							k.remove(i);
//						}
//					if(k.size() == 0) {
//						leftR += costSize;
//						return true;
//					}
//				}
//			}
//			return false;
//		}
//		else {
//			right.addCoins(rightR);
//			return true;
//		}
	}
	public int getVP()
	{
		return vp;
	}
	public boolean hasResource(String res) {
		if(resources.containsKey(res))
			return true;
		return false;
	}
	public LinkedHashMap<String, ArrayList<Card>> getCards() { return cards; }
	
	public int getPlayerNum() { return playerNum; }
	public void setLeft() { brownLeft = true;}
	public void setRight() { brownRight = true; }
	public void setBoth() { silverBoth = true; }
	public int getLosses() { return loss; }
	public int getBrownNum() { return cards.get("brown").size(); }
	public int getSilverNum() { return cards.get("silver").size(); }
	public int getGoldNum() { return cards.get("gold").size(); }
	public int getGreenNum() { return cards.get("green").size(); }
	public int getRedNum() { return cards.get("red").size(); }
	public int getBlueNum() { return cards.get("blue").size(); }
	public void addVP(int num) { vp+= num; }
	public int getPurpleNum() { return cards.get("purple").size(); }
}
