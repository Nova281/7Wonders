import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GameState {

	private int age;
	private Player currentPlayer;
	private LinkedHashMap<Player, ArrayList<Card>> playerHands;
	private Player[] players;
	private int playerNum;
	private boolean endOfGame;

	public GameState() {
		setEnd(false);
		age = 1;
		players = new Player[3];
		playerHands = new LinkedHashMap<Player, ArrayList<Card>>();
		// assigns wonder
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

		for (int i = 0; i < 3; i++) {
			players[i] = new Player(i + 1);
			players[i].setWonder(wonderArr.remove((int) (Math.random() * wonderArr.size())));
			playerHands.put(players[i], new ArrayList<Card>());
		}
		// assigns wonder^
		currentPlayer = players[0];
		playerNum = 1;
		currentPlayer.setLeft(players[1]); // player 2
		currentPlayer.setRight(players[2]); // player 3
	}

	// playerNum can be 1, 2, or 3
	public Player[] getPlayers() {
		return players;
	}

	public ArrayList<Card> getHand(int playerNum) {
		return playerHands.get(players[playerNum - 1]);
	}

	public Player getLeftPlayer() {
		return currentPlayer.getLeft();
	}

	public Player getRightPlayer() {
		return currentPlayer.getRight();
	}

	public int getAge() {
		return age;
	}

	public ArrayList<Card> getCurrentHand() {
		return playerHands.get(currentPlayer);
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public LinkedHashMap<Player, ArrayList<Card>> getPlayerHands() {
		return playerHands;
	}

	public Player getPlayer(int playerNum) {
		return players[playerNum - 1];
	}

	public int getCurrentPlayerNum() {
		return playerNum;
	}

//	public void updateState(Player cp, int a, LinkedHashMap<Player, ArrayList<Card>> ph) {
//		currentPlayer = cp;
//		age = a;
//		playerHands = ph;
//		playerNum = cp.getPlayerNum();
//		if(playerNum == 1) {
//			left = players[3];
//			right = players[2];
//		}
//		else if(playerNum == 2) {
//			left = players[1];
//			right = players[3];
//		}
//		else {
//			left = players[2];
//			right = players[1];
//		}
//	}	
//	public void updateState(Player cp) {
//		currentPlayer = cp;
//		playerNum = cp.getPlayerNum();
//		if(playerNum == 1) {
//			currentPlayer.setLeft(players[3]);
//			currentPlayer.setRight(players[2]);
//		}
//		else if(playerNum == 2) {
//			currentPlayer.setLeft(players[1]);
//			currentPlayer.setRight(players[3]);
//		}
//		else {
//			currentPlayer.setLeft(players[2]);
//			currentPlayer.setRight(players[1]);
//		}
//	}
	public void updateState(int age) {
		this.age = age;
	}

	public void updateState(LinkedHashMap<Player, ArrayList<Card>> ph) {
		playerHands = ph;
	}

	public void updateState(ArrayList<Card> hand) {
		playerHands.put(currentPlayer, hand);
	}

	public void nextTurn() {
		// out.println(left + " " + currentPlayer + " " + right);
		Player tempCP = currentPlayer;
		currentPlayer = currentPlayer.getLeft();
		currentPlayer.setLeft(tempCP.getRight());
		currentPlayer.setRight(tempCP);
		playerNum = currentPlayer.getPlayerNum();

		// out.println(left + " " + currentPlayer + " " + right);
	}

	public void setEnd(boolean b) {
		endOfGame = b;
	}

	public boolean ifEnd() {
		return endOfGame;
	}
}
