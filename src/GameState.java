import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import static java.lang.System.*;

import java.io.IOException;

public class GameState {
	
	private int age;
	private Board board;
	private LinkedHashMap<Player, ArrayList<Card>> players;
	
	public GameState() {
		board = new Board();
		age = 1;
		players = new LinkedHashMap<>();
		
		Alexandria a = new Alexandria();
		Babylon b = new Babylon();
		Ephesus e = new Ephesus();
		Gizah g = new Gizah();
		Halikarnassos h = new Halikarnassos();
		Olympia o = new Olympia();
		Rhodos r = new Rhodos();
		ArrayList<Wonder> wonderArr = new ArrayList<>();
		wonderArr.add(a);
		wonderArr.add(b);
		wonderArr.add(e);
		wonderArr.add(g);
		wonderArr.add(h);
		wonderArr.add(o);
		wonderArr.add(r);
		
		for(int i = 1; i < 4; i++) {
			players.put(new Player(i), new ArrayList<Card>());
			getPlayer(i).setWonder(wonderArr.remove((int) (Math.random() * wonderArr.size())));
		}
		
	}
	//playerNum can be 1, 2, or 3
	public void setWonder(int playerNum, Wonder wonder) {
		Player p = getPlayer(playerNum);
		p.setWonder(wonder);
	}
	
	public void deal() {
		board.deal(players);
//		board.printPlayerHand(players, 1);
//		board.printPlayerHand(players, 2);
//		board.printPlayerHand(players, 3);
	}
	
	public void printPlayerHand(int playerNum) {
		out.println(players.get(getPlayer(playerNum)));
		//out.println(players.get(getPlayer(playerNum)));
	}
	
	public Board getBoard() { return board; }
	//playerNum can be 1, 2, 3
	public Player getPlayer(int playerNum) {
		Iterator<Player> iter = players.keySet().iterator();
		Player p = new Player(0);
		for(int i = 0; i < playerNum; i++)
			p = iter.next();
		return p;
	}
	//playerNum can be 1, 2, 3
	public void discard(int playerNum, Card choice) {
		Player p = getPlayer(playerNum);
		board.discard(p, players.get(p), choice);
	}
	
	public void build(int playerNum, Card choice) {
		Player p = getPlayer(playerNum);
		board.build(p, players.get(p), choice);
	}
	
	public void sacrifice(int playerNum, Card choice) {
		Player p = getPlayer(playerNum);
		board.sacrifice(p, players.get(p), choice);
	}
	public void passCards() {
		players = board.passCards(age, players);
	}
	public int nextAge() {
		for(int i = 1; i < 4; i++)
			players.put(getPlayer(i), new ArrayList<>());
		try {
			board.createDeck(++age);
		} catch (IOException e) { }
		board.deal(players);
		return age;
	}
	public int getAge() { return age; }
	
	public LinkedHashMap<Player, ArrayList<Card>> getPlayerMap() { return players; }
	
//	public boolean buildWonder(int playerNum) {
//		return getPlayer(playerNum).buildWonder();
//	}
}
