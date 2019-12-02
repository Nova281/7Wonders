import java.io.IOException;

public class GameState {
	
	private int age;
	private Player[] players;
	private Board board;
	
	public GameState() throws IOException {
		board = new Board();
		age = 1;
		players = new Player[3];
		for(int i = 0; i < players.length; i++)
			players[i] = new Player();
	}
	
	public void setWonder(int player, Wonder wonder) {
		players[player-1].setWonder(wonder);
	}
	
	public void run() {
		board.deal(players[0], players[1], players[2]);
	}
}
