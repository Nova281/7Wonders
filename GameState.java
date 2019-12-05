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
		for(int i = 0; i < 3; i++)
			players.put(new Player(), new ArrayList<Card>());
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
		for(Player k: players.keySet())
			out.println(players.get(k));
		//out.println(players.get(getPlayer(playerNum)));
	}
	
	public Board getBoard() { return board; }
	//playerNum can be 1, 2, 3
	public Player getPlayer(int playerNum) {
		Iterator<Player> iter = players.keySet().iterator();
		Player p = new Player();
		for(int i = 0; i < 3; i++)
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
//	public boolean canBuildWonder(int playerNum) {
//		return getPlayer(playerNum).canBuildWonder();
//	}
}
