import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
	private GameState gs;
	public MainFrame(String title)
	{
		super(title);
		gs = new GameState();
		
	}
}
