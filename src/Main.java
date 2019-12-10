import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		//graphics
		GameState gs = new GameState(); //will be in graphics, just do graphics.getGameState();
		gs.deal();
		for(int i = 1; i < 4; i++) {
			out.println("Player " + gs.getPlayer(i).getPlayerNum() + ": ");
			gs.printPlayerHand(i);
			out.println("Wonder: ");
			out.println(gs.getPlayer(i).getWonder());
		}
		
		
	}

}
