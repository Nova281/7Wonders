import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.imageio.ImageIO;

public class GamePanel extends JPanel {
	
	private Wonder currentBoard;
	private BufferedImage wonderImg;
	
	public GamePanel()
	{
	
		currentBoard = null;
		wonderImg = ImageIO.read(getClass().getResource(currentBoard.getName())+"tint");
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(wonderImg, 0, 0, 1920, 1080, this);
	}
	
	public Wonder updateCurrentBoard(Player currentPlayer)
	{
		currentBoard = currentPlayer.getWonder();
	}
	
	
}
