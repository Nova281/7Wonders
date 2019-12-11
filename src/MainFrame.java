
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private GameState gs;
	private GamePanel panel;
	private ArrayList<Player> playerList;

	public MainFrame(String title, GameState gs) throws IOException, FontFormatException {
		super(title);
		this.gs = gs;
		setupGraphics();

	}

	public void setupGraphics() throws IOException, FontFormatException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		panel = new GamePanel(gs);

		panel.setWonderImages();
		panel.updateCurrentBoard(gs.getCurrentPlayer());
		panel.updateCurrentAge(gs.getAge());
		panel.updateCoins();
		panel.updatePlayerHand(gs.getCurrentHand());
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}

	public void updateGraphics() throws IOException {
		panel.updateCurrentBoard(gs.getCurrentPlayer());
		panel.updateCurrentAge(gs.getAge());
		panel.updateCoins();
		panel.updatePlayerHand(gs.getCurrentHand());
		repaint();
	}

	public void updateCurrentPlayer(Player p) throws IOException {
		panel.updateCurrentBoard(p);
		repaint();
	}

	public void updatePlayerCoins() {
		panel.updateCoins();
		repaint();
	}

	public void updateCurrentAge(int i) throws IOException {
		panel.updateCurrentAge(i);
		repaint();
	}

	public void updatePlayerCards(ArrayList<Card> cardList) {
		panel.updatePlayerHand(cardList);
		repaint();
	}

}
