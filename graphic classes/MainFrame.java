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
		String[] wonderAr = {"Gizah", "Olympia", "Rhodos"};
		panel = new GamePanel();
		panel.setWonderImages(wonderAr);
		panel.updateCurrentBoard("Rhodos");
		panel.updateCurrentAge(2);
		add(panel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(true);
		pack();
		setVisible(true);
	}
	
}
