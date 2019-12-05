import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Wonder[] wonderList;
	// private String wonderP1, wonderP2, wonderP3;
	private BufferedImage[] imgList;
	private BufferedImage currentWonder, currentAgeCard, coin1, coin3;
	private Font font;
	private String currentAgeStr;
	private int currentPlayer, coinOne, coinThree;
	private Player[] playerList;
	private int[] coin1List, coin3List;

	public GamePanel() throws IOException, FontFormatException {
		wonderList = new Wonder[3];
		imgList = new BufferedImage[3];
		playerList = new Player[3];
		coin1List = new int[3];
		coin3List = new int[3];
		currentAgeStr = "";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
		g.drawString(": " + coin1List[(currentPlayer + 2) % 3], 1745, 1025);
		g.drawString(": " + coin3List[(currentPlayer + 2) % 3], 1745, 1125);

		// Player after
		g.drawImage(imgList[(currentPlayer + 3) % 3], 0, 0, 960, 150, 0, 360, 1920, 720, this);
		g.drawString("Player " + ((currentPlayer + 3) % 3 + 1), 440, 150);
		g.drawImage(coin1, 10, 10, 40, 40, this);
		g.drawImage(coin3, 10, 60, 40, 40, this);
		if (playerList[(currentPlayer + 3) % 3].getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(": " + coin1List[(currentPlayer + 3) % 3], 55, 35);
			g.drawString(": " + coin3List[(currentPlayer + 3) % 3], 55, 85);
			g.setColor(Color.WHITE);
		}
		else
		{
			g.drawString(":  " + coin1List[(currentPlayer + 3) % 3], 55, 35);
			g.drawString(":  " + coin3List[(currentPlayer + 3) % 3], 55, 85);
		}
		

		// Player before
		g.drawImage(imgList[(currentPlayer + 4) % 3], 960, 0, 1920, 150, 0, 360, 1920, 720, this);
		g.drawString("Player " + ((currentPlayer + 4) % 3 + 1), 1400, 150);
		g.drawImage(coin1, 970, 10, 40, 40, this);
		g.drawImage(coin3, 970, 60, 40, 40, this);
		if (playerList[(currentPlayer + 4) % 3].getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(": " + coin1List[(currentPlayer + 4) % 3], 1015, 50);
			g.drawString(": " + coin3List[(currentPlayer + 4) % 3], 1015, 100);
			g.setColor(Color.WHITE);
		}
		else	
		{
			g.drawString(": " + coin1List[(currentPlayer + 4) % 3], 1015, 50);
			g.drawString(": " + coin3List[(currentPlayer + 4) % 3], 1015, 100);
		}
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
	
	public void updateCoins()
	{
		int totalCoins = 0;
		for (int i = 0; i < playerList.length; i++)
		{
			totalCoins = playerList[i].getCoins();
			coin3List[i] = totalCoins%3;
			totalCoins-=totalCoins%3;
			coin1List[i] = totalCoins;
		}
	}

}
