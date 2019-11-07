import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel {
	private Wonder currentBoard = null; 
	
	if (currentBoard.getName().equals("Alexandria"))
	{
		
	}
	
	public Wonder updateCurrentBoard(Player currentPlayer)
	{
		currentBoard = currentPlayer.getWonder();
	}
}
