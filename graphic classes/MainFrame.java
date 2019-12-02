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
		panel = new GamePanel();
		panel.updateCurrentBoard("Olympia");
		add(panel);
		setLayout(null);
		setVisible(true);
	}
	
}
