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
	//p can be 1, 2, or 3
	public void printPlayerHand(LinkedHashMap<Player, ArrayList<Card>> playerMap, int p) {
		Iterator<Player> iter = playerMap.keySet().iterator();
		for(int i = 0; i < p; i++)
			Player cp = iter.next();
		out.println(playerMap.get(p));
	}
	
}
