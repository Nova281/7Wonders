import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Wonder[] wonderList;
	// private String wonderP1, wonderP2, wonderP3;
	private BufferedImage[] imgList;
	private BufferedImage[] imgList1, hand;
	private BufferedImage currentWonder, currentAgeCard, coin1, coin3, stage1, stage2, stage3;
	private Font font;
	private String currentAgeStr;
	private int currentPlayer, coinOne, coinThree, width;
	private Player[] playerList;
	private int[] coin1List, coin3List;

	public GamePanel() throws IOException, FontFormatException {
		wonderList = new Wonder[3];
		imgList = new BufferedImage[3];
		imgList1 = new BufferedImage[3];
		playerList = new Player[3];
		coin1List = new int[3];
		coin3List = new int[3];
		currentAgeStr = "";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width;
		setSize(screenSize.width, screenSize.height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		/*
		 * wonderImg = ImageIO.read(getClass().getResource(currentBoard+".jpg"));
		 * wonderImage1 = ImageIO.read(getClass().getResource("Gizah.jpg"));
		 * wonderImage2 = ImageIO.read(getClass().getResource("Olympia.jpg"));
		 * wonderImage3 = ImageIO.read(getClass().getResource("Rhodos.jpg"));
		 */
		coin1 = ImageIO.read(getClass().getResource("coin1.png"));
		coin3 = ImageIO.read(getClass().getResource("coin3.png"));

		String fName = "PossumSaltareNF.ttf";
		InputStream is = GamePanel.class.getResourceAsStream(fName);
		font = Font.createFont(Font.TRUETYPE_FONT, is);
		font = font.deriveFont(Font.BOLD, 20);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// current Player
		g.drawImage(imgList[(currentPlayer + 2) % 3], 0, 0, 1920, 1080, this);

		g.setColor(new Color(145, 127, 107, 200));
		g.fillRect(0, 780, 1920, 1080);
		g.setColor(new Color(120, 108, 92, 200));
		g.fillRect(0, 760, 1920, 20);

		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Player " + currentPlayer, 900, 778);
		g.drawImage(currentAgeCard, 0, 780, this);
		g.drawImage(coin1, 1700, 950, 40, 40, this);
		g.drawImage(coin3, 1700, 1000, 40, 40, this);
		g.drawString(":  " + coin1List[(currentPlayer + 2) % 3], 1745, 975);
		g.drawString(":  " + coin3List[(currentPlayer + 2) % 3], 1745, 1025);
		// current Player resource
		String resourceFile = playerList[(currentPlayer + 2) % 3].getWonder().getResource() + ".jpg";
		try {
			g.drawImage(ImageIO.read(getClass().getResource(resourceFile)), 0, 710, 170, 50, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// effects
		g.drawImage(imgList1[0], 480, 680, 230, 80, this);
		g.drawImage(imgList1[1], 840, 680, 230, 80, this);
		g.drawImage(imgList1[2], 1190, 680, 230, 80, this);

		// Player after (testing Player 2)
		g.drawImage(imgList[(currentPlayer + 3) % 3], 0, 0, 960, 150, 0, 360, 1920, 720, this);
		g.drawString("Player " + ((currentPlayer + 3) % 3 + 1), 440, 150);
		g.drawImage(coin1, 10, 5, 40, 40, this);
		g.drawImage(coin3, 10, 55, 40, 40, this);
		if (playerList[(currentPlayer + 3) % 3].getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(":  " + coin1List[(currentPlayer + 3) % 3], 55, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 3) % 3], 55, 80);
			g.setColor(Color.WHITE);
		} else {
			g.drawString(":  " + coin1List[(currentPlayer + 3) % 3], 55, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 3) % 3], 55, 80);
		}

		resourceFile = playerList[(currentPlayer + 3) % 3].getWonder().getResource() + ".jpg";
		try {
			g.drawImage(ImageIO.read(getClass().getResource(resourceFile)), 0, 100, 170, 50, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Player before (testing Player 3)
		g.drawImage(imgList[(currentPlayer + 4) % 3], 960, 0, 1920, 150, 0, 360, 1920, 720, this);
		g.drawString("Player " + ((currentPlayer + 4) % 3 + 1), 1400, 150);
		g.drawImage(coin1, 970, 5, 40, 40, this);
		g.drawImage(coin3, 970, 55, 40, 40, this);
		if (playerList[(currentPlayer + 4) % 3].getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(":  " + coin1List[(currentPlayer + 4) % 3], 1015, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 4) % 3], 1015, 80);
			g.setColor(Color.WHITE);
		} else {
			g.drawString(":  " + coin1List[(currentPlayer + 4) % 3], 1015, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 4) % 3], 1015, 80);
		}

		resourceFile = playerList[(currentPlayer + 4) % 3].getWonder().getResource() + ".jpg";
		try {
			g.drawImage(ImageIO.read(getClass().getResource(resourceFile)), 960, 100, 170, 50, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * for (int i = 0; i < hand.length; i++) { g.drawImage(hand[i], 350 + i * 30,
		 * 250 + i * 50, 144, 220, this); }
		 */
		int i = 0;
		for (Map.Entry<String, ArrayList<Card>> entry : playerList[(currentPlayer + 2) % 3].getCards().entrySet()) {
			String key = entry.getKey();
			ArrayList<Card> value = entry.getValue();
			// System.out.println(value);
			for (int j = 0; j < value.size(); j++) {
				System.out.println((value.get(j).getName()).toLowerCase());
				try {
					g.drawImage(ImageIO.read(getClass().getResource((value.get(j).getName()).toLowerCase() + ".png")),
							200 + i + j * 30, 200 + j * 50, 144, 220, this);
					System.out.println((350 + i + j * 30) + ", " + (200 + j * 50));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error");
				}
			}
			i += 115;
		}
		/*
		 * try { int i = 0; g.drawImage(hand[i], 0 + i * 30, 0 * i + 50, 144, 220,
		 * this); i++; } catch (Exception e) { System.out.println("Error"); }
		 */
		/*
		 * ArrayList<Card> currentPlayerHand = playerList[(currentPlayer + 2) %
		 * 3].getHand(); for (int i = 0; i < currentPlayerHand.size(); i++) {
		 * 
		 * g.drawImage(hand[i], 0 + i * 30, 0 + i * 50, 144, 220, this); try {
		 * g.drawImage(ImageIO.read(getClass().getResource(currentPlayerHand.get(i) +
		 * ".png")), 0 + i * 30, 0 + i * 50, 144, 220, this);
		 * 
		 * } catch (Exception e) { // TODO: handle exception
		 * System.out.println("Error"); }
		 * 
		 * }
		 */

	}

	// updated currentWonder onto graphics from Player
	public void updateCurrentBoard(String playerName) throws IOException {
		for (int i = 1; i <= imgList.length; i++) {
			int playerNum = Integer.parseInt(playerName);
			if (i == playerNum)
				currentPlayer = i;
		}
	}

	// receive list of players & get each player's wonder name to set image
	public void setWonderImages(Player[] players) throws IOException {
		for (int i = 0; i < wonderList.length; i++) {
			wonderList[i] = players[i].getWonder();
			imgList[i] = ImageIO.read(getClass().getResource(players[i].getWonder().getName() + ".jpg"));
		}
		playerList = players;
	}

	// sets current age w/ age card
	public void updateCurrentAge(int i) throws IOException {
		currentAgeStr = "age" + i + ".png";
		currentAgeCard = ImageIO.read(getClass().getResource(currentAgeStr));
	}

	public void updateCoins() {
		int totalCoins = 0;
		for (int i = 0; i < playerList.length; i++) {
			totalCoins = playerList[i].getCoins();
			coin3List[i] = totalCoins / 3;
			totalCoins -= coin3List[i] * 3;
			coin1List[i] = totalCoins;
		}
	}

	public void updateWonderEffects() throws IOException {
		String wonderName = playerList[(currentPlayer + 2) % 3].getWonder().getName();
		// stage1 = ImageIO.read(new File("/wonder_effects/" + wonderName +
		// "/Stage1.jpg"));
		for (int i = 0; i < imgList1.length; i++) {
			imgList1[i] = ImageIO.read(getClass().getResource(wonderName + "Stage" + (i + 1) + ".jpg"));
		}
	}

	/*
	 * public void updateCurrentHand() throws IOException { ArrayList<Card>
	 * currentPlayerHand = playerList[(currentPlayer + 2) % 3].getHand(); hand = new
	 * BufferedImage[currentPlayerHand.size()]; for (Card c : currentPlayerHand) {
	 * System.out.println(c); } for (int i = 0; i < currentPlayerHand.size(); i++) {
	 * BufferedImage card =
	 * ImageIO.read(getClass().getResource(currentPlayerHand.get(i).getName() +
	 * ".png")); hand[i] = card; }
	 */
}
