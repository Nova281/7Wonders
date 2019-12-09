import java.awt.Dimension;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	// private GameState gs;
	private GamePanel panel;

	public MainFrame(String title) throws IOException, FontFormatException {
		super(title);
		setupGraphics();

	}

	public void setupGraphics() throws IOException, FontFormatException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		panel = new GamePanel();
		Player one = new Player(), two = new Player(), three = new Player();
		Alexandria a = new Alexandria();
		Babylon b = new Babylon();
		Ephesus e = new Ephesus();
		Gizah g = new Gizah();
		Halicarnassus h = new Halicarnassus();
		Olympia o = new Olympia();
		Rhodos r = new Rhodos();
		ArrayList<Wonder> wonderAr = new ArrayList<>();
		wonderAr.add(a);
		wonderAr.add(b);
		wonderAr.add(e);
		wonderAr.add(g);
		wonderAr.add(h);
		wonderAr.add(o);
		wonderAr.add(r);
		one.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		two.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		three.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		one.addCoins(2);
		BlueCard altar = new BlueCard("Altar", "blue", "1", " ", "Temple", " ");
		BlueCard palace = new BlueCard("Palace;blue;3;glass, papyrus, cloth, clay, wood, ore, stone; ; ");
		BlueCard pantheon = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		BlueCard pantheon1 = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		GreenCard academy = new GreenCard("Academy;green;3;stone, stone, stone, glass; ;School");
		one.addCard(altar);
		one.addCard(academy);
		one.addCard(palace);
		one.addCard(pantheon);
		one.addCard(pantheon1);
		two.addCoins(1);
		three.addCoins(5);

		/*
		 * one.setWonder(wonderAr.get(3)); two.setWonder(wonderAr.get(5));
		 * three.setWonder(wonderAr.get(6));
		 */
		Player[] playerAr = { one, two, three };
		panel.setWonderImages(playerAr);
		panel.updateCurrentBoard("1");
		panel.updateCurrentAge(1);
		panel.updateCoins();
		panel.updateWonderEffects();
		// panel.updateCurrentHand();
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}

	public void updateGraphics() throws IOException {
		panel.updateCurrentBoard("2");
		panel.updateCurrentAge(3);
		// panel.updateCoins();
		panel.updateWonderEffects();
		// panel.updateCurrentHand();
		repaint();
	}

}
