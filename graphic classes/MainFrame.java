import java.awt.*;
import java.io.IOException;

import javax.swing.*;

public class MainFrame extends JFrame {
	//private GameState gs;
	private GamePanel panel;
	public MainFrame(String title) throws IOException
	{
		super(title);
		setupGraphics();
	}
	
	public void setupGraphics() throws IOException
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize.width, screenSize.height);
		String[] wonderAr = {"Olympia", "Giza", "Rhodos"};
		panel = new GamePanel();
		panel.setWonderImages(wonderAr);
		panel.updateCurrentBoard("Rhodos");
		add(panel);
		setLayout(null);
		setVisible(true);
	}
	
}
