import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel {
	
	private String currentBoard;
	private BufferedImage wonderImg;
	
	public GamePanel() throws IOException
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		wonderImg = ImageIO.read(getClass().getResource(currentBoard+".jpg"));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(wonderImg, 0, 0, 1920, 1080, this);
	}
	
	public void updateCurrentBoard(String currentWonder)
	{
		currentBoard = currentWonder;
	}
	
	
}
