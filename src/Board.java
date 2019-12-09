import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Iterator;
import static java.lang.System.*;

public class Board {
	
	private ArrayList<Card> deck = new ArrayList<>();
	
	public Board() {
		try {
			createDeck(1);
		} catch (IOException e) {}
	}
	
	public void createDeck(int a) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("age" + a + ".txt"));
		StringTokenizer st;
		deck = new ArrayList<>();
		ArrayList<ActionCard> guildCards = new ArrayList<>();
		while(br.ready()) {
			st = new StringTokenizer(br.readLine(), ";");
			String name = st.nextToken();
			String color = st.nextToken();
			String age = st.nextToken();
			if(color.equals("brown") || color.equals("silver") || name.equals("caravansery") || name.equals("Forum"))
				deck.add(new ResourceCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("blue"))
				deck.add(new BlueCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("red"))
				deck.add(new RedCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("green"))
				deck.add(new GreenCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("gold"))
				deck.add(new ActionCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else {
				guildCards.add(new ActionCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			}
		}
		
		if(a == 3) {
			Collections.shuffle(guildCards);
			for(int i = 4; i >= 0; i--)
				guildCards.remove(i);
			for(Card c: guildCards)
				deck.add(c);
		}
		Collections.shuffle(deck);
	}
	
	public void printDeck() {
		Collections.sort(deck);
		System.out.println("DECK:");
		for(Card c: deck)
			System.out.println(c);
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void deal(LinkedHashMap<Player, ArrayList<Card>> playerMap) {
		Collections.shuffle(deck);
		out.println(deck);
		Iterator<Player> iter = playerMap.keySet().iterator();
		for(int i = 0; i < 3; i++) {
			Player cp = iter.next();
			ArrayList<Card> cc = playerMap.get(cp);
			for(int j = 6; j >= 0; j--) {
				cc.add(deck.remove(j));
				playerMap.put(cp, cc);
			}
			Collections.sort(playerMap.get(cp));
		}
	}
	public ArrayList<Card> discard(Player p, ArrayList<Card> currentHand, Card c) {
		p.addCoins(3);
		currentHand.remove(c);
		return currentHand;
	}
	public ArrayList<Card> build(Player p, ArrayList<Card> currentHand, Card c) {
		p.addCard(c);
		currentHand.remove(c);
		return currentHand;
	}
	public ArrayList<Card> sacrifice(Player p, ArrayList<Card> currentHand, Card c) {
//		p.buildWonder();
		currentHand.remove(c);
		return currentHand;
	}
	//p can be 1, 2, or 3
	public void printPlayerHand(LinkedHashMap<Player, ArrayList<Card>> playerMap, int p) {
		Iterator<Player> iter = playerMap.keySet().iterator();
		Player cp = new Player(0);
		for(int i = 0; i < p; i++)
			cp = iter.next();
		out.println(playerMap.get(cp));
	}
	public LinkedHashMap<Player, ArrayList<Card>> passCards(int age, LinkedHashMap<Player, ArrayList<Card>> players) {
		Iterator<Player> iter = players.keySet().iterator();
		ArrayList<Card> temp;
		Player p1 = iter.next();
		Player p2 = iter.next();
		Player p3 = iter.next();
		if(age == 2) {
			temp = players.get(p1);
			players.put(p1, players.get(p2));
			players.put(p2, players.get(p3));
			players.put(p3, temp);
		}
		else {
			temp = players.get(p1);
			players.put(p1, players.get(p3));
			players.put(p3, players.get(p2));
			players.put(p2, temp);
		}
		return players;
	}
}
