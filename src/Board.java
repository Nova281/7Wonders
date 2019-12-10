import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.Iterator;
import static java.lang.System.*;

public class Board {
	
	private ArrayList<Card> deck = new ArrayList<>();
	private MainFrame mf;
	private GameState gs;
	
	public Board() {
		gs = new GameState();
//		try {
//			mf = new MainFrame("7 Wonders");
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (FontFormatException e) {
//			e.printStackTrace();
//		}
		nextAge();
	}
	
	public void createDeck() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("age" + gs.getAge() + ".txt"));
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
		
		if(gs.getAge() == 3) {
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
	
	public void nextAge() {
		if(gs.getAge() == 3)
			return;
		LinkedHashMap<Player, ArrayList<Card>> ph = gs.getPlayerHands();
		
		gs.updateState(gs.getAge()+1);
		gs.updateState(ph);
		try {
			createDeck();
		} catch (IOException e) {
			e.printStackTrace();
		}
		deal();
	}
	
	public ArrayList<Card> getDeck() {
		return deck;
	}
	
	public void deal() {
		Collections.shuffle(deck);
		LinkedHashMap<Player, ArrayList<Card>> ph = gs.getPlayerHands();
		for(int i = 0; i < 3; i++)
			ph.put(gs.getPlayers()[i], new ArrayList<>());
		Iterator<Player> iter = ph.keySet().iterator();
		for(int i = 0; i < 3; i++) {
			Player cp = iter.next();
			ArrayList<Card> cc = ph.get(cp);
			for(int j = 6; j >= 0; j--) {
				cc.add(deck.remove(j));
				ph.put(cp, cc);
			}
			Collections.sort(ph.get(cp));
		}
		//out.println(ph);
		gs.updateState(ph);
		//out.println(gs.getPlayerHands());
	}
	
	public void discard(Card c) {
		gs.getCurrentPlayer().addCoins(3);
		gs.getCurrentHand().remove(c);
	}
	public boolean build(Card c) {
		if(gs.getCurrentPlayer().canBuild(c)) {
			gs.getCurrentPlayer().addCard(c, gs.getLeftPlayer(), gs.getRightPlayer());
			gs.getPlayerHands().remove(c);
			return true;
		}
		return false;
	}
	public boolean sacrifice(Card c) {
		if(gs.getCurrentPlayer().canBuildWonder()) {
			LinkedHashMap<Player, ArrayList<Card>> ph = gs.getPlayerHands();
			ph.remove(c);
			gs.getCurrentPlayer().buildWonder();
			gs.updateState(ph);
			return true;
		}
		return false;
	}
	
	public void printPlayerHand(int pNum) {
		out.println(gs.getPlayerHands().get(gs.getPlayer(pNum)));
	}
	public void passCards() {
		LinkedHashMap<Player, ArrayList<Card>> ph = gs.getPlayerHands();
		Iterator<Player> iter = ph.keySet().iterator();
		ArrayList<Card> temp;
		Player p1 = iter.next();
		Player p2 = iter.next();
		Player p3 = iter.next();
		if(gs.getAge() == 2) {
			temp = ph.get(p1);
			ph.put(p1, ph.get(p2));
			ph.put(p2, ph.get(p3));
			ph.put(p3, temp);
		}
		else {
			temp = ph.get(p1);
			ph.put(p1, ph.get(p3));
			ph.put(p3, ph.get(p2));
			ph.put(p2, temp);
		}
		gs.updateState(ph);
	}
	
	public void wageWar() {
		int age = gs.getAge();
		Player p1 = gs.getPlayer(1);
		Player p2 = gs.getPlayer(2);
		Player p3 = gs.getPlayer(3);
		p1.wageWar(age, p2);
		p2.wageWar(age, p3);
		p3.wageWar(age, p1);
	}
	
	public void runTurn(Scanner input) {
		Player cp = gs.getCurrentPlayer();
		ArrayList<Card> ch = gs.getCurrentHand();
		out.println("This is your current hand:\n" + ch);
		out.println("These are your cards:\n" + cp.getCards());
		out.println("----------------------");
		out.println("What would you like to do? (Enter number)\n1. Build\n2. Sacrifice\n3. Discard");
		int choice = input.nextInt();
		if(choice == 1) {
			out.println("Which card would you like to build? (Enter number: 0,1,2...)");
			int bc = input.nextInt();
			if(build(ch.get(bc)))
				System.out.println("These are your cards: " + cp.getCards());
			else {
				out.println("You cannot do this, choose another option.");
				runTurn(input);
			}
		}
		else if(choice == 2) {
			out.println("Which card would you like to sacrifice? (Enter number: 0,1,2...)");
			int sc = input.nextInt();
			if(sacrifice(ch.get(sc))) {
				String built = "";
				if(cp.getWonder().getPhaseState(1))
					built+="Phase 1 ";
				if(cp.getWonder().getPhaseState(2))
					built+="Phase 2 ";
				if(cp.getWonder().getPhaseState(3))
					built+="Phase 3 ";
				if(built.length() == 0)
					built = "nothing";
				out.println("You have built " + built + ".");
				out.println(cp.getWonder().getPhaseState(1));
			}
			else {
				out.println("You cannot do this, choose another option.");
				out.println("----------------------");
				runTurn(input);
			}
		}
		else if(choice == 3) {
			out.println("Which card would you like to discard for 3 coins? (Enter number: 0,1,2...)");
			int dc = input.nextInt();
			discard(ch.get(dc));
			out.println("You now have " + cp.getCoins() + " coins.");
		}
	}
	public int score(Player p) {
		int score = 0;
		score+=p.getVP()+p.getMP();
		score+=p.getCoins()/3;
		//phases accounted for in player
		int num1 = p.getSciences().get("math");
		int num2 = p.getSciences().get("literature");
		int num3 = p.getSciences().get("engineering");
		score+=7*Math.min(num1, Math.min(num1, num2));
		score+=Math.pow(num1, 2) + Math.pow(num2, 2) + Math.pow(num3, 2);
		return score;
	}
	public void run() {
		Scanner input = new Scanner(System.in);
		for(int x = 1; x < 4; x++) {
			try {
				createDeck();
			} catch (IOException e) {
				e.printStackTrace();
			}
			deal();
			out.println("Player 1's wonder: " + gs.getPlayer(1).getWonder().getName());
			out.println("Player 2's wonder: " + gs.getPlayer(2).getWonder().getName());
			out.println("Player 3's wonder: " + gs.getPlayer(3).getWonder().getName());
//			out.println("Age" + gs.getAge());
//			printPlayerHand(1);
//			printPlayerHand(2);
//			printPlayerHand(3);
			for(int i = 0; i < 6; i++) {
				for(int j = 1; j < 4; j++) {
					out.println("----------------------");
					out.println("Player " + j + "'s turn");
					runTurn(input);
					gs.nextTurn();
				}
				passCards();
			}
			wageWar();
			nextAge();
		}
		int p1Score = score(gs.getPlayer(1));
		int p2Score = score(gs.getPlayer(2));
		int p3Score = score(gs.getPlayer(3));
		out.println("Player 1 ended with a score of " + p1Score);
		out.println("Player 2 ended with a score of " + p2Score);
		out.println("Player 3 ended with a score of " + p3Score);
		int big = Math.max(p1Score, Math.max(p2Score, p3Score));
		if(p1Score == big)
			out.println("Player 1 wins!");
		else if(p2Score == big)
			out.println("Player 2 wins!");
		else
			out.println("Player 3 wins!");
		
	}
}
