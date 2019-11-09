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
		wonderImg = ImageIO.read(getClass().getResource(currentBoard.getName()));
		tintBkgd = ImageIO.read(getClass().getResource("TintBkgd"));
	}
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(wonderImg, 0, 0, 1920, 1080, this);
		g.drawImage(TintBkgd.)
		
	}
	
	public Wonder updateCurrentBoard(Player currentPlayer)
	{
		currentBoard = currentPlayer.getWonder();
	}
}
