
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements MouseListener {
	private GameState gs;
	private GamePanel panel;
	private ArrayList<Player> playerList;

	public MainFrame(String title, GameState gs) throws IOException, FontFormatException {
		super(title);
		//addMouseListener(new MouseAdapter());
		this.gs = gs;
		playerList = new ArrayList<>();
		Player[] playerAr = gs.getPlayers();
		for (int i = 0; i < playerAr.length; i++)
		{
			playerList.add(playerAr[i]);
		}
		setupGraphics(playerList);

	}

	public void setupGraphics(ArrayList<Player> playerList2) throws IOException, FontFormatException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);

		panel = new GamePanel(playerList2);

		panel.setWonderImages(playerList2);
		panel.setWonderEffects();
		panel.updateCurrentBoard(1);
		panel.updateCurrentAge(1/*gs.getAge()*/);
		panel.updateCoins();
		panel.updatePlayerHand(gs.getHand(1));
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}

	public void updateCurrentPlayer(int i) throws IOException {
		panel.updateCurrentBoard(i);
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

	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
