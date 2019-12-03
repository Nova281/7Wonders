import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GamePanel extends JPanel {
	
	private String[] wonderList;
	//private String wonderP1, wonderP2, wonderP3;
	private BufferedImage[] imgList;
	private BufferedImage wonderImage1, wonderImage2, wonderImage3, currentWonder;
	
	
	public GamePanel() throws IOException
	{
		wonderList = new String[3];
		imgList = new BufferedImage[3];
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		//wonderImg = ImageIO.read(getClass().getResource(currentBoard+".jpg"));
		wonderImage1 = ImageIO.read(getClass().getResource("Gizah.jpg"));
		wonderImage2 = ImageIO.read(getClass().getResource("Olympia.jpg"));
		wonderImage3 = ImageIO.read(getClass().getResource("Rhodos.jpg"));
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(currentWonder, 0, 0, 1920, 1080, this);
		
		g.setColor(new Color(145, 127, 107, 200));
		g.drawRect(0, 780, 1920, 1080);
		
		g.drawImage(wonderImage1, 0, 0 , 960, 150, 0, 360, 1920, 720,  this);
		g.drawImage(wonderImage2, 960, 0 , 1920, 150, 0, 360, 1920, 720,  this);
	}
	
	public void updateCurrentBoard(String wonder) throws IOException
	{
		for (int i = 0; i < wonderList.length; i++)
		{
			if (wonderList[i].equals(wonder))
				currentWonder = ImageIO.read(getClass().getResource(wonder+".jpg"));
		}
	}
	
	public void setWonderImages(String[] wonderAr) throws IOException
	{
		for (int i = 0; i < wonderList.length; i++)
		{
			wonderList[i] = wonderAr[i];
		}
	}
	
	
}
