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
	private TreeMap<String, Integer> sciences;
	
	private boolean brownLeft = false;
	private boolean brownRight = false;
	private boolean hasMarket = false; //what is this stuff
	
	
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
	
	public TreeMap<String, Integer> getSciences() { return sciences; }
	
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
	
	public void addCard(Card card, Player p1, Player p2)
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
				hasMarket = true;
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
		if(cost.size() > choiceRes.size())
			return false;
		else if(cost.size() > 0) {
			//ArrayList<String> c = new ArrayList<String>();
			ArrayList<String> strCombos = new ArrayList<>();
			ArrayList<ArrayList<String>> combinations = new ArrayList<>();
			createCombo(choiceRes, strCombos, 0, "");
			String[] a;
			for(String k: strCombos) {
				ArrayList<String> temp = new ArrayList<>();
				a = k.split(";");
				for(int i = 1; i < a.length; i++)
					temp.add(a[i]);
				combinations.add(temp);
			}
			if(combinations.contains(cost))				
				return true;
			else {
				for(ArrayList<String> k: combinations) {
					for(int i = cost.size()-1; i >= 0; i--)
						if(k.contains(cost.get(i)))
							k.remove(i);
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
	private void createCombo(ArrayList<ArrayList<String>> lists, ArrayList<String> combinations, int runs, String current) {
	    if (runs == lists.size()) {
	        combinations.add(current);
	        return;
	    }
	    for (int i = 0; i < lists.get(runs).size(); i++) {
	        createCombo(lists, combinations, runs + 1, current + ";" + lists.get(runs).get(i));
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
		runWonder(stage);
		if(wonder.getName().equals("Alexandria"))
		{
			Alexandria alex = (Alexandria)wonder;
			if(stage == 2)
			{
				ArrayList<String> choi = alex.runPhase2();
				addChoiceRes(choi);
				wonder.setPhase(stage);
			}
		}
		else if(wonder.getName().equals("Babylon"))
		{
			Babylon b = (Babylon)wonder;
			/*if(stage == 2)
			{
				ArrayList<String> choi = b.runPhase2();
				addChoiceRes(choi);
				wonder.setPhase(stage);
			}*/
		}
		else if(wonder.getName().equals("Ephesus"))
		{
			Ephesus e = (Ephesus)wonder;
			runWonder(stage);
			if(stage == 2)
			{
				int coi = e.runPhase2();
				addCoins(coi);
				wonder.setPhase(stage);
			}
		}
		else if(wonder.getName().equals("Gizah"))
		{
			Gizah g = (Gizah)wonder;
			if(stage == 2)
			{
				vp += g.runPhase2();
				wonder.setPhase(stage);
			}
		}
		else if(wonder.getName().equals("Halicarnassus"))
		{
			Halikarnassos h = (Halikarnassos)wonder;
			/*if(stage == 2)
			{
				ArrayList<String> choi = h.runPhase2();
				addChoiceRes(choi);
				wonder.setPhase(stage);
			}*/
		}
		else if(wonder.getName().equals("Olympia"))
		{
			Olympia o = (Olympia)wonder;
			/*if(stage == 2)
			{
				ArrayList<String> choi = o.runPhase2();
				addChoiceRes(choi);
				wonder.setPhase(stage);
			}*/
		}
		else if(wonder.getName().equals("Rhodos"))
		{
			Rhodos alex = (Rhodos)wonder;
			if(stage == 2)
			{
				mp += alex.runPhase2();
				wonder.setPhase(stage);
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
	
	public String toString() { return Integer.toString(playerNum); }
	public int getWP() { return warPoints; }
	public Wonder getWonder() { return wonder; }
	
	public boolean canTrade(Player left, Player right, ArrayList<String> cost)
	{
		String[] brown = {"wood", "stone", "ore", "clay"};
		ArrayList<String> brownList = (ArrayList<String>) Arrays.asList(brown);
		
		int coinCost = 0;
		for(int i = cost.size()-1; i >= 0; i--) {
			String r = cost.get(i);
			if(left.hasResource(r)) {
				cost.remove(r);
				if(brownList.contains(r)) {
					if(brownLeft)
						coinCost++;
					else 
						coinCost+=2;
				}
				else {
					if(hasMarket)
						coinCost++;
					else
						coinCost+=2;
				}
			}
			else if(right.hasResource(r)) {
				cost.remove(r);
				if(brownList.contains(r)) {
					if(brownRight)
						coinCost++;
					else
						coinCost+=2;
				}
				else {
					if(hasMarket)
						coinCost++;
					else
						coinCost+=2;
				}
			}
		}
		if(cost.size() == 0 && coins - coinCost >= 0)
			return true;
		
		//check through left choice res
		//creates possible combinations
		ArrayList<ArrayList<String>> leftChoice = left.getChoiceRes();
		ArrayList<String> strCombos = new ArrayList<>();
		ArrayList<ArrayList<String>> combos = new ArrayList<>();
		createCombo(leftChoice, strCombos, 0, "");
		String[] arr;
		for(String k: strCombos) {
			ArrayList<String> temp = new ArrayList<>();
			arr = k.split(";");
			for(int i = 1; i < arr.length; i++)
				temp.add(arr[i]);
			combos.add(temp);
		}
		//creates possible combinations^
		int tempCC = coinCost, smallCC = 0;
		ArrayList<String> small = new ArrayList<>(cost);
		for(ArrayList<String> k: combos) {
			tempCC = coinCost;
			for(int i = cost.size()-1; i >= 0; i--)
				if(k.contains(cost.get(i))) {
					String r = k.remove(i);
					if(brownList.contains(r)) {
						if(brownLeft)
							tempCC++;
						else 
							tempCC+=2;
					}
					else {
						if(hasMarket)
							tempCC++;
						else
							tempCC+=2;
					}
				}
			if(k.size() == 0 && coins-coinCost >= 0) {
				return true;
			}
			else if(k.size() < small.size()) {
				small = k;
				smallCC = tempCC;
			}
		}
		cost = small;
		coinCost = smallCC;
		//check through right choice res
		ArrayList<ArrayList<String>> rightChoice = right.getChoiceRes();
		strCombos = new ArrayList<>();
		combos = new ArrayList<>();
		createCombo(rightChoice, strCombos, 0, "");
		for(String k: strCombos) {
			ArrayList<String> temp = new ArrayList<>();
			arr = k.split(";");
			for(int i = 1; i < arr.length; i++)
				temp.add(arr[i]);
			combos.add(temp);
		}
		tempCC = coinCost;
		for(ArrayList<String> k: combos) {
			tempCC = coinCost;
			for(int i = cost.size()-1; i >= 0; i--)
				if(k.contains(cost.get(i))) {
					String r = k.remove(i);
					if(brownList.contains(r)) {
						if(brownRight)
							tempCC++;
						else 
							tempCC+=2;
					}
					else {
						if(hasMarket)
							tempCC++;
						else
							tempCC+=2;
					}
				}
			if(k.size() == 0) {
				coinCost = tempCC;
				if(coins-coinCost >= 0)
					return true;
				else
					return false;
			}
		}
		return false;
	}

	public void trade(Player left, Player right, ArrayList<String> cost)
	{
		String[] brown = {"wood", "stone", "ore", "clay"};
		ArrayList<String> brownList = (ArrayList<String>) Arrays.asList(brown);
		
		int lb = 0, ls = 0, rb = 0, rs = 0;
		for(int i = cost.size()-1; i >= 0; i--) {
			if(left.hasResource(cost.get(i))) {
				String r = cost.remove(i);
				if(brownList.contains(r)) {
					if(brownLeft)
						lb++;
					else 
						lb+=2;
				}
				else {
					if(hasMarket)
						ls++;
					else
						ls+=2;
				}
			}
			else if(right.hasResource(cost.get(i))) {
				String r = cost.remove(i);
				if(brownList.contains(r)) {
					if(brownRight)
						rb++;
					else 
						rb+=2;
				}
				else {
					if(hasMarket)
						rs++;
					else
						rs+=2;
				}
			}
		}
		if(cost.size() == 0) {
			right.addCoins(rb+rs);
			left.addCoins(lb+ls);
			coins-=(rb+rs+lb+ls);
			return; //worked
		}
		
		//check through left choice res
		//creates combos
		ArrayList<ArrayList<String>> leftChoice = left.getChoiceRes();
		ArrayList<String> strCombos = new ArrayList<>();
		ArrayList<ArrayList<String>> combos = new ArrayList<>();
		createCombo(leftChoice, strCombos, 0, "");
		String[] arr;
		for(String k: strCombos) {
			ArrayList<String> temp = new ArrayList<>();
			arr = k.split(";");
			for(int i = 1; i < arr.length; i++)
				temp.add(arr[i]);
			combos.add(temp);
		}
		//creates combos^
		int tempLB, tempLS, smallLB = 0, smallLS = 0; //leftremovedbrown/silver
		ArrayList<String> small = new ArrayList<>(cost);
		for(ArrayList<String> k: combos) {
			tempLB = lb;
			tempLS = ls;
			for(int i = cost.size()-1; i >= 0; i--)
				if(k.contains(cost.get(i))) {
					String r = k.remove(i);
					if(brownList.contains(r)) {
						if(brownLeft)
							tempLB++;
						else 
							tempLB+=2;
					}
					else {
						if(hasMarket)
							tempLS++;
						else
							tempLS+=2;
					}
				}
			if(k.size() == 0) {
				left.addCoins(tempLB + tempLS);
				right.addCoins(rb+rs);
				coins-=(tempLB+tempLS+rb+rs);
				return; //worked
			}
			else if(k.size() < small.size()) {
				small = k;
				smallLB = tempLB;
				smallLS = tempLS;
			}
		}
		cost = small;
		lb = smallLB;
		ls = smallLS;
		//check through right choice res
		ArrayList<ArrayList<String>> rightChoice = right.getChoiceRes();
		strCombos = new ArrayList<>();
		combos = new ArrayList<>();
		createCombo(rightChoice, strCombos, 0, "");
		for(String k: strCombos) {
			ArrayList<String> temp = new ArrayList<>();
			arr = k.split(";");
			for(int i = 1; i < arr.length; i++)
				temp.add(arr[i]);
			combos.add(temp);
		}
		int tempRB, tempRS; //leftremovedbrown/silver
		small = new ArrayList<>(cost);
		for(ArrayList<String> k: combos) {
			tempRB = rb;
			tempRS = rs;
			for(int i = cost.size()-1; i >= 0; i--)
				if(k.contains(cost.get(i))) {
					String r = k.remove(i);
					if(brownList.contains(r)) {
						if(brownRight)
							tempRB++;
						else 
							tempRB+=2;
					}
					else {
						if(hasMarket)
							tempRS++;
						else
							tempRS+=2;
					}
				}
			if(k.size() == 0) {
				left.addCoins(lb + ls);
				right.addCoins(tempRB + tempRS);
				coins-=(tempRB+tempRS+lb+ls);
				return; //worked
			}
		}
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
	
	public boolean canBuildWonder() {
		int stage = 0;
		if(wonder.getPhaseState(1) == false)
			stage = 1;
		else if(wonder.getPhaseState(2) == false)
			stage = 2;
		else if(wonder.getPhaseState(3) == false)
			stage = 3;
		else
			return false;
		String[] array = wonder.getResWonder(stage).split(" ");
		ArrayList<String> res = new ArrayList<>();
		for(String k: array)
			res.add(k);
		return check(res);
	}
	
	public LinkedHashMap<String, ArrayList<Card>> getCards() { return cards; }
	
	public int getPlayerNum() { return playerNum; }
	public void setLeft() { brownLeft = true;}
	public void setRight() { brownRight = true; }
	public void setBoth() { hasMarket = true; }
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
