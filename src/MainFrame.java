
import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainFrame extends JFrame implements MouseListener {
	private GameState gs;
	private GamePanel panel;
	private ArrayList<Player> playerList;

	public MainFrame(String title, GameState gs) throws IOException, FontFormatException {
		super(title);
		this.gs = gs;
		setupGraphics();
		addMouseListener(this);

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
		panel.updatePlayerHand();
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
		panel.updatePlayerHand();
		repaint();
	}

	public void updateCurrentPlayer(Player p) throws IOException {
		panel.updateCurrentBoard(p);
	}

	public void updatePlayerCoins() {
		panel.updateCoins();
	}

	public void updateCurrentAge(int i) throws IOException {
		panel.updateCurrentAge(i);
	}

	public void updatePlayerCards() {
		panel.updatePlayerHand();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		// int size = tempHand.size();
		System.out.println(x + ", " + y);
		for (int i = 0; i < gs.getXCoords().size(); i++) {
			if ((x >= gs.getXCoords().get(i) && x <= gs.getXCoords().get(i) + 180) && (y >= 780 && y <= 1080)) {
				gs.setCardIndex(i);
				int cardIndex = gs.getCardIndex();
				gs.setClickCard(true);
				repaint();

				if ((x >= gs.getXCoords().get(cardIndex) + 68 && x <= gs.getXCoords().get(cardIndex) + 128)
						&& (y >= 800 && y <= 846)) {
					{
						gs.getCurrentPlayer().addCard(gs.getCurrentHand().remove(cardIndex));
					}
					if ((x >= gs.getXCoords().get(cardIndex) + 68 && x <= gs.getXCoords().get(cardIndex) + 128)
							&& (y >= 900 && y <= 946)) {
						if (gs.getCurrentPlayer().canBuild(gs.getCurrentHand().get(cardIndex))) {
							gs.getCurrentPlayer().addCard(gs.getCurrentHand().remove(cardIndex));
							gs.getCurrentHand().remove(cardIndex);
						}
					}

					if ((x >= gs.getXCoords().get(cardIndex) + 78 && x <= gs.getXCoords().get(cardIndex) + 113)
							&& (y >= 1000 && y <= 1042)) {
						gs.getCurrentPlayer().addCoins(3);
						gs.getCurrentHand().remove(cardIndex);
					}

					gs.nextTurn();
					// updatePlayerHand(gs.getCurrentHand());
					try {
						updateCurrentPlayer(gs.getCurrentPlayer());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					repaint();

				}
			}
			gs.setClickCard(false);
			if ((x >= 920 && x <= 952) && (y >= 125 && y <= 143)) {
				if (gs.getPressedDownL() == false)
					gs.setPressedDownL(true);
				else
					gs.setPressedDownL(false);
				repaint();
			}
			if ((x >= 1880 && x <= 1898) && (y >= 125 && y <= 143)) {
				if (gs.getPressedDownR() == false)
					gs.setPressedDownL(true);
				else
					gs.setPressedDownR(false);
				repaint();
			}
		}
	}

}
