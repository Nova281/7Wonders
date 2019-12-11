
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements MouseListener {

	private Wonder[] wonderList;
	private GameState gs;
	// private String wonderP1, wonderP2, wonderP3;
	private BufferedImage[] imgList;
	private BufferedImage[] imgList1, hand;
	private BufferedImage currentWonder, currentAgeCard, coin1, coin3, stage1, stage2, stage3;
	private Font font;
	private String currentAgeStr;
	private int currentPlayer, coinOne, coinThree, width;
	private ArrayList<Player> playerList;
	private int[] coin1List, coin3List;
	private ArrayList<Card> tempHand;
	private Player currentP;

	public GamePanel(GameState gs) throws IOException, FontFormatException {
		playerList = new ArrayList<>();
		this.gs = gs;
		currentP = null;
		wonderList = new Wonder[3];
		imgList = new BufferedImage[3];
		imgList1 = new BufferedImage[3];
		Player[] playerAr = gs.getPlayers();
		for (int i = 0; i < playerAr.length; i++)
			playerList.add(playerAr[i]);
		for (Player p : this.playerList)
			System.out.println(p.getWonder().getName());
		coin1List = new int[3];
		coin3List = new int[3];
		tempHand = new ArrayList<>();
		currentAgeStr = "";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.width;
		setSize(screenSize.width, screenSize.height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		coin1 = ImageIO.read(getClass().getResource("/tokens/coin1.png"));
		coin3 = ImageIO.read(getClass().getResource("/tokens/coin3.png"));

		String fName = "/font/PossumSaltareNF.ttf";
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
		g.drawString(("Player " + currentPlayer).toUpperCase(), 900, 778);
		g.drawImage(currentAgeCard, 0, 780, this);
		g.drawImage(coin1, 1700, 950, 40, 40, this);
		g.drawImage(coin3, 1700, 1000, 40, 40, this);
		g.drawString(":  " + coin1List[(currentPlayer + 2) % 3], 1745, 975);
		g.drawString(":  " + coin3List[(currentPlayer + 2) % 3], 1745, 1025);
		// current Player resource
		String resourceFile = "/wonder_resource/" + playerList.get((currentPlayer + 2) % 3).getWonder().getResource()
				+ ".jpg";
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
		g.drawString(("Player " + ((currentPlayer + 3) % 3 + 1)).toUpperCase(), 440, 150);
		g.drawImage(coin1, 10, 5, 40, 40, this);
		g.drawImage(coin3, 10, 55, 40, 40, this);
		if (playerList.get((currentPlayer + 3) % 3).getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(":  " + coin1List[(currentPlayer + 3) % 3], 55, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 3) % 3], 55, 80);
			g.setColor(Color.WHITE);
		} else {
			g.drawString(":  " + coin1List[(currentPlayer + 3) % 3], 55, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 3) % 3], 55, 80);
		}

		resourceFile = "/wonder_resource/" + playerList.get((currentPlayer + 3) % 3).getWonder().getResource() + ".jpg";
		try {
			g.drawImage(ImageIO.read(getClass().getResource(resourceFile)), 0, 100, 170, 50, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// Player before (testing Player 3)
		g.drawImage(imgList[(currentPlayer + 4) % 3], 960, 0, 1920, 150, 0, 360, 1920, 720, this);
		g.drawString(("Player " + ((currentPlayer + 4) % 3 + 1)).toUpperCase(), 1400, 150);
		g.drawImage(coin1, 970, 5, 40, 40, this);
		g.drawImage(coin3, 970, 55, 40, 40, this);
		if (playerList.get((currentPlayer + 4) % 3).getWonder().getName().equals("Olympia")) {
			g.setColor(Color.BLACK);
			g.drawString(":  " + coin1List[(currentPlayer + 4) % 3], 1015, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 4) % 3], 1015, 80);
			g.setColor(Color.WHITE);
		} else {
			g.drawString(":  " + coin1List[(currentPlayer + 4) % 3], 1015, 30);
			g.drawString(":  " + coin3List[(currentPlayer + 4) % 3], 1015, 80);
		}

		resourceFile = "/wonder_resource/" + playerList.get((currentPlayer + 4) % 3).getWonder().getResource() + ".jpg";
		try {
			g.drawImage(ImageIO.read(getClass().getResource(resourceFile)), 960, 100, 170, 50, this);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int i = 0;
		for (Map.Entry<String, ArrayList<Card>> entry : playerList.get((currentPlayer + 2) % 3).getCards().entrySet()) {
			String key = entry.getKey();
			ArrayList<Card> value = entry.getValue();
			// System.out.println(value);
			// System.out.println(value);
			for (int j = 0; j < value.size(); j++) {

				try {
					g.drawImage(
							ImageIO.read(getClass()
									.getResource("/cards/" + (value.get(j).getName()).toLowerCase() + ".png")),
							100 + i + j * 30, 200 + j * 50, 144, 220, this);
					System.out.println((0 + i + j * 30) + ", " + (200 + j * 50));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Error");
				}
			}

			if (value.size() <= 2) {
				i += 150 * value.size() * 2;
			} else
				i += 300;

		}

		int count = 0;
		for (Card c : tempHand) {
			try {
				g.drawImage(ImageIO.read(getClass().getResource("/cards/" + c.getName() + ".png")), 300 + count, 780,
						this);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count += 200;
		}

	}

	// updated currentWonder & its Phases onto graphics from Player
	public void updateCurrentBoard(Player p) throws IOException {
		for (int i = 1; i <= imgList.length; i++) {
			if (i == p.getPlayerNum())
				currentPlayer = i;

		}
		String wonderName = playerList.get((currentPlayer + 2) % 3).getWonder().getName();

		for (int i = 0; i < imgList1.length; i++) {
			imgList1[i] = ImageIO
					.read(getClass().getResource("/wonder_effects/" + wonderName + "Stage" + (i + 1) + ".jpg"));
		}

	}

	// receive list of players & get each player's wonder name to set image
	public void setWonderImages(ArrayList<Player> playerList2) throws IOException {

		for (int i = 0; i < playerList2.size(); i++) {
			Player p = playerList2.get(i);
			imgList[i] = ImageIO.read(getClass().getResource("/wonder_boards/" + p.getWonder().getName() + ".jpg"));
		}

	}

	// sets current age w/ age card
	public void updateCurrentAge(int i) throws IOException {
		currentAgeStr = "age" + i + ".png";
		currentAgeCard = ImageIO.read(getClass().getResource("/cards/" + currentAgeStr));
	}

	public void updateCoins() {
		int totalCoins = 0;
		for (int i = 0; i < playerList.size(); i++) {
			totalCoins = playerList.get(i).getCoins();
			coin3List[i] = totalCoins / 3;
			totalCoins -= coin3List[i] * 3;
			coin1List[i] = totalCoins;
			totalCoins = 0;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();

		int size = tempHand.size();
		System.out.println(x + ", " + y);
		if ((x >= 300 && x <= 480) && (y >= 780 && y <= 1080)) {
			gs.getCurrentPlayer().addCard(tempHand.remove(0), gs.getRightPlayer(), gs.getLeftPlayer());
			gs.updateState(gs.getLeftPlayer());
			repaint();
			System.out.println("Worked");

		}

	}

	public void updatePlayerHand(ArrayList<Card> arrayList) {
		tempHand = arrayList;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

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

}
