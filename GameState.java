import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.Iterator;
import static java.lang.System.*;

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
		Iterator<Player> iter = players.keySet().iterator();
		for(int i = 0; i < playerNum-1; i++)
			iter.next();
		iter.next().setWonder(wonder);
	}
	
	public void run() {
		board.deal(players);
		board.printPlayerHand()
	}
	
	public void printPlayers() {
		Iterator<Player> iter = players.keySet().iterator();
		for(int i = 0; i < 3; i++)
			out.println(iter.next());
	}
	
	public Board getBoard() { return board; }
	public LinkedHashMap<Player, ArrayList<Card>> getPlayerMap() { return players; }
}
