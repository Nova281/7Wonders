import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class MainFrame extends JFrame {
	//private GameState gs;
	private GamePanel panel;
	public MainFrame(String title) throws IOException, FontFormatException
	{
		super(title);
		setupGraphics();
	}
	
	public void setupGraphics() throws IOException, FontFormatException
	{
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
		Wonder[] wonderAr = {a, b, e, g, h, o ,r};
		one.setWonder(wonderAr[(int)(Math.random()*wonderAr.length)]);
		two.setWonder(wonderAr[(int)(Math.random()*wonderAr.length)]);
		three.setWonder(wonderAr[(int)(Math.random()*wonderAr.length)]);
		Player[] playerAr = {one, two, three};
		panel.setWonderImages(playerAr);
		panel.updateCurrentBoard("3");
		panel.updateCurrentAge(2);
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}
	
}
