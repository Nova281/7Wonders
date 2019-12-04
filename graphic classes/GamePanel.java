import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel {
	
	private String[] wonderList;
	//private String wonderP1, wonderP2, wonderP3;
	private BufferedImage[] imgList;
	private BufferedImage currentWonder, currentAgeCard;
	private Font font;
	private String currentAgeStr;
	private int currentPlayer;
	
	
	public GamePanel() throws IOException, FontFormatException
	{
		wonderList = new String[3];
		imgList = new BufferedImage[3];
		currentAgeStr = "";
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		/*wonderImg = ImageIO.read(getClass().getResource(currentBoard+".jpg"));
		wonderImage1 = ImageIO.read(getClass().getResource("Gizah.jpg"));
		wonderImage2 = ImageIO.read(getClass().getResource("Olympia.jpg"));
		wonderImage3 = ImageIO.read(getClass().getResource("Rhodos.jpg"));*/
		String fName = "PossumSaltareNF.ttf";
	    InputStream is = GamePanel.class.getResourceAsStream(fName);
	    font = Font.createFont(Font.TRUETYPE_FONT, is);
	    font = font.deriveFont(Font.BOLD, 20);
	    
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(currentWonder, 0, 0, 1920, 1080, this);
		
		g.setColor(new Color(145, 127, 107, 200));
		g.fillRect(0, 780, 1920, 1080);
		g.setColor(new Color(120, 108, 92, 200));
		g.fillRect(0, 760, 1920, 20);
		
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString("Player " + currentPlayer, 900, 778);
		g.drawImage(currentAgeCard, 0, 780, this);
		
		g.drawImage(imgList[currentPlayer-3], 0, 0 , 960, 150, 0, 360, 1920, 720,  this);
		g.drawString("Player " + (currentPlayer-2), 440, 150);
		g.drawImage(imgList[currentPlayer-2], 960, 0 , 1920, 150, 0, 360, 1920, 720,  this);
		g.drawString("Player " + (currentPlayer-1), 1400, 150);
	}
	
	// updated currentWonder onto graphics
	public void updateCurrentBoard(String playerName) throws IOException
	{
		for (int i = 1; i <= imgList.length; i++)
		{
			int playerNum = Integer.parseInt(playerName);
			if (i == playerNum)
			{
				currentWonder = imgList[i-1];
				currentPlayer = i;
			}
			
		}
	}
	// receive list of players & get each player's wonder name to set image
	public void setWonderImages(String[] wonderAr) throws IOException 
	{
		for (int i = 0; i < wonderList.length; i++)
		{
			wonderList[i] = wonderAr[i];
			imgList[i] = ImageIO.read(getClass().getResource(wonderAr[i]+".jpg"));
		}
	}
	
	// sets current age w/ age card
	public void updateCurrentAge(int i) throws IOException
	{
		currentAgeStr = "age"+i+".png";
		currentAgeCard = ImageIO.read(getClass().getResource(currentAgeStr));
	}
	
	
}
