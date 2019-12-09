

import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JFrame;



public class MainFrame extends JFrame {
	private GameState gs;
	private GamePanel panel;
	private Player[] playerList;

	public MainFrame(String title) throws IOException, FontFormatException {
		super(title);
		gs = new GameState();
		//setupGraphics(playerList);

	}

	public void setupGraphics(Player[] playerList, LinkedHashMap<Player, ArrayList<Card>> playerMap) throws IOException, FontFormatException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		panel = new GamePanel();

		/*
		 * one.setWonder(wonderAr.get(3)); two.setWonder(wonderAr.get(5));
		 * three.setWonder(wonderAr.get(6));
		 */
		panel.setWonderImages(playerList);
		panel.setWonderEffects();
		panel.updateCurrentBoard(1);
		panel.updateCurrentAge(1);
		panel.updateCoins();
		panel.updatePlayerHand(playerMap);
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}

	public void updateCurrentPlayer(Player p) throws IOException {
		panel.updateCurrentBoard(p.getPlayerNum());
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
	public void updatePlayerCards(LinkedHashMap<Player, ArrayList<Card>> playerMap)
	{
		panel.updatePlayerHand(playerMap);
		repaint();
	}

}
