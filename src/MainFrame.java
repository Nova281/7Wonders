
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

	public MainFrame(String title) throws IOException, FontFormatException {
		super(title);
		addMouseListener(new MouseAdapter());
		gs = new GameState();
		playerList = new ArrayList<>();
		setupGraphics(gs);

	}

	public void setupGraphics(GameState gs) throws IOException, FontFormatException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		for (Map.Entry<Player, ArrayList<Card>> entry : gs.getPlayerMap().entrySet()) {
			playerList.add(entry.getKey());
		}
		panel = new GamePanel(playerList);

		panel.setWonderImages(gs.getPlayerMap());
		panel.setWonderEffects();
		panel.updateCurrentBoard((gs.getTurn() + 2) % 3 + 1);
		panel.updateCurrentAge(gs.getAge());
		panel.updateCoins();
		panel.updatePlayerHand(gs.getPlayerMap());
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

	public void updatePlayerCards(LinkedHashMap<Player, ArrayList<Card>> playerMap) {
		panel.updatePlayerHand(playerMap);
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
