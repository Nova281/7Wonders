import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.TreeMap;

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
	private int mp; // military points
	private TreeMap<String, Integer> sciences;

	private boolean brownLeft = false;
	private boolean brownRight = false;
	private boolean silverBoth = false; // what is this stuff

	public Player() {
		vp = 0;
		warPoints = 0;
		wins = 0;
		loss = 0;
		coins = 0;
		cards = new TreeMap<String, ArrayList<Card>>();
		String[] colors = { "brown", "silver", "blue", "red", "gold", "green", "purple" };
		for (String k : colors)
			cards.put(k, new ArrayList<>());
		resources = new TreeMap<String, Integer>();
		choiceRes = new ArrayList<>();
		sciences = new TreeMap<String, Integer>();
	}

	// sets war points, takes in age and if won
	public void setWar(int age, boolean win) {
		if (win == false) {
			loss++;
			warPoints -= 1;
		} else if (age == 1) {
			warPoints += 1;
			wins++;
		} else if (age == 2) {
			warPoints += 3;
			wins++;
		} else {
			warPoints += 5;
			wins++;
		}
	}

	public void addCard(Card card) {
		String color = card.getColor();
		ArrayList<Card> crd = cards.get(color);
		crd.add(card);
		Collections.sort(crd);
		cards.put(color, crd);

		if (color.equals("brown") || color.equals("silver")) {
			ResourceCard c = (ResourceCard) card;
			if (c.isChoice())
				choiceRes.add(c.getResources());
			else {
				ArrayList<String> resources = c.getResources();
				for (int i = 0; i < resources.size(); i++) {
					String resource = resources.get(i);
					if (this.resources.containsKey(resource))
						this.resources.put(resource, this.resources.get(resource) + 1);
					else
						this.resources.put(resource, 1);
				}
			}

		} else if (color.equals("red")) {
			RedCard c = (RedCard) card;
			mp += c.getMP();
		} else if (color.equals("green")) {
			GreenCard c = (GreenCard) card;
			String science = c.getScience();
			if (cards.containsKey(science))
				sciences.put(science, (sciences.get(science) + 1));
			else
				sciences.put(science, 1);
		} else if (color.equals("blue")) {
			BlueCard c = (BlueCard) card;
			vp += c.getVP();
		}
		// idk about action card
		else if (color.equals("gold") || color.equals("purple")) {
			ActionCard c = (ActionCard) card;
			if (c.getEffect().equals("right 1"))
				brownRight = true;
			else if (c.getEffect().equals("left 1"))
				brownLeft = true;
			else if (c.getEffect().equals("both 1"))
				silverBoth = true;
			else if (c.getEffect().equals("loom glass papyrus")) {
				String[] array = c.getEffect().split(" ");
				ArrayList<String> re = new ArrayList<String>();
				for (int i = 0; i < array.length; i++) {
					re.add(array[i]);
				}
				choiceRes.add(re);
			} else if (c.getEffect().equals("clay stone ore wood")) {
				String[] array = c.getEffect().split(" ");
				ArrayList<String> re = new ArrayList<String>();
				for (int i = 0; i < array.length; i++) {
					re.add(array[i]);
				}
				choiceRes.add(re);
			} else if (c.getEffect().equals("1 brown")) {
				int num = cards.get("brown").size();
				coins += num;
				vp += num;
			} else if (c.getEffect().equals("1 gold")) {
				int num = cards.get("gold").size();
				coins += num;
				vp += num;
			} else if (c.getEffect().equals("3 1 wonder")) {
				int num = 0;
				if (wonder.getPhaseState(1) == true)
					num++;
				else if (wonder.getPhaseState(2) == true)
					num++;
				else if (wonder.getPhaseState(3) == true)
					num++;
				coins += (3 * num);
				vp += num;
			}
		}
	}

	public boolean canBuild(Card card) {
		if (card.getCost().size() == 1 && card.getCost().get(0).equals(" "))
			return true;
		ArrayList<String> cost = card.getCost();
		System.out.println(cost);
		for (String resource : resources.keySet()) {
			for (int i = 0; i < resources.get(resource); i++)
				if (cost.contains(resource))
					cost.remove(resource);
				else
					break;
		}
		System.out.println(cost);
		return false; // finish later
//		 ArrayList<String> res = card.getCost();
//		 TreeMap<String, Integer> resM = new TreeMap<String, Integer>();
//		 ArrayList<String> reso = new ArrayList<String>();
//		 boolean ret = true;
//		 for(int i = 0; i < res.size(); i++)
//		 {
//			 String re = res.get(i);
//			 if(resM.get(re) == null)
//			 {
//				 resM.put(re,1);
//			 }
//			 else
//			 {
//				 int num = resM.get(re);
//				 resM.put(re,(num+1));
//			 }
//		 }
//		 Set<String> keys = resM.keySet();
//		 for(String key : keys)
//		 {
//			 int num = resM.get(key);
//			 if(resources.get(key) >= num && ret == true)
//				 ret = true;
//			 else
//			 {
//				 int no = num - resources.get(key);
//				 if(no > 0)
//				 {
//					 resources.put(key, no);
//				     ret = false;
//				     for(int i = 0; i < no; i++)
//				     {
//				    	 reso.add(key);
//				     }
//				 }
//			 }
//		 }
//		 ArrayList<ArrayList<String>> resou = new ArrayList<ArrayList<String>>();
//		 for(int i = 0; i < choiceRes.size(); i++)
//		 {
//			 resou.add(choiceRes.get(i));
//		 }
//		 while(reso.size() > 0)
//		 {
//			 String word = reso.get(0);
//			 if(resou.contains(word))
//			 {
//				 reso.remove(0);
//			 }
//			 else
//				 break;
//		 }
//		 if(reso.size() == 0)
//			 ret = true;
//		 return ret;
	}

	/*
	 * private boolean check(ArrayList<String> list) {
	 * 
	 * }
	 */
	public int getMP() {
		return mp;
	}

	public boolean hasCard(Card card) {
		String cardName = card.getName();
		Set<String> keys = cards.keySet();
		boolean has = false;
		for (String key : keys) {
			ArrayList<Card> cardss = cards.get(key);
			for (Card crd : cardss) {
				if (crd.getName().equalsIgnoreCase(cardName)) {
					has = true;
				}
			}
		}
		return has;
	}

	public void addCoins(int value) {
		coins += value;
	}

	public int getCoins() {
		return coins;
	};

	public void setWonder(Wonder wonder) {
		this.wonder = wonder;
	}
	/*
	 * public void buildWonder() {
	 * 
	 * } private void wonderRes(int stage) { if(!wonder.getPhaseState(stage)) {
	 * 
	 * } }
	 */

	public void addChoiceRes(ArrayList<String> list) {
		choiceRes.add(list);
	}

	public void addScience(String s) {
		if (sciences.containsKey(s))
			sciences.put(s, sciences.get(s) + 1);
		else
			sciences.put(s, 1);
	}

	public TreeMap<String, ArrayList<Card>> getCards() {
		return cards;
	}

	public String toString() {
		return cards.toString();
	}

	public int getWP() {
		return warPoints;
	}

	public Wonder getWonder() {
		return wonder;
	}
}
