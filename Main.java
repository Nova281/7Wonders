import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		//graphics
		GameState gs = new GameState(); //will be in graphics, just do graphics.getGameState();
		gs.deal();
		out.println("Original");
		for(int i = 1; i < 4; i++) {
			out.print("Player " + i + ": ");
			gs.printPlayerHand(i);
		}
		//gs.setWonder(graphics.getWonderChoice()); do this in graphics
		gs.passCards();
		out.println("Original after passing");
		for(int i = 1; i < 4; i++) {
			out.print("Player " + i + ": ");
			gs.printPlayerHand(i);
		}
		gs.nextAge();
		out.println("original next age");
		for(int i = 1; i < 4; i++) {
			out.print("Player " + i + ": ");
			gs.printPlayerHand(i);
		}
	}

}
