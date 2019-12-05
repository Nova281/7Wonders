import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

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
		ArrayList<Wonder> wonderAr = new ArrayList<>(); 
		wonderAr.add(a); wonderAr.add(b); wonderAr.add(e); wonderAr.add(g); 
		wonderAr.add(h); wonderAr.add(o); wonderAr.add(r);
		one.setWonder(wonderAr.remove((int)(Math.random()*wonderAr.size())));
		two.setWonder(wonderAr.remove((int)(Math.random()*wonderAr.size())));
		three.setWonder(wonderAr.remove((int)(Math.random()*wonderAr.size())));
		one.addCoins(2);
		two.addCoins(1);
		three.addCoins(5);
		
		/*one.setWonder(wonderAr.get(3));
		two.setWonder(wonderAr.get(5));
		three.setWonder(wonderAr.get(6));*/
		Player[] playerAr = {one, two, three};
		panel.setWonderImages(playerAr);
		panel.updateCurrentBoard("1");
		panel.updateCurrentAge(2);
		panel.updateCoins();
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}
	
}
