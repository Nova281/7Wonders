

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;



public class Runner {
	public static void main(String[] args) throws IOException, FontFormatException {

		Player one = new Player(1), two = new Player(2), three = new Player(3);
		Alexandria a = new Alexandria();
		Babylon b = new Babylon();
		Ephesus e = new Ephesus();
		Gizah g = new Gizah();
		Halicarnassus h = new Halicarnassus();
		Olympia o = new Olympia();
		Rhodos r = new Rhodos();
		ArrayList<Wonder> wonderAr = new ArrayList<>();
		wonderAr.add(a);
		wonderAr.add(b);
		wonderAr.add(e);
		wonderAr.add(g);
		wonderAr.add(h);
		wonderAr.add(o);
		wonderAr.add(r);
		one.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		two.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		three.setWonder(wonderAr.remove((int) (Math.random() * wonderAr.size())));
		BlueCard altar = new BlueCard("Altar", "blue", "1", " ", "Temple", " ");
		BlueCard palace = new BlueCard("Palace;blue;3;glass, papyrus, cloth, clay, wood, ore, stone; ; ");
		BlueCard pantheon = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		BlueCard pantheon1 = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		GreenCard academy = new GreenCard("Academy;green;3;stone, stone, stone, glass; ;School");
		ResourceCard stone = new ResourceCard("Stone Pit;brown;1; ; ; ");
		one.addCard(altar);
		one.addCard(academy);
		one.addCard(palace);
		one.addCard(pantheon);
		one.addCard(pantheon1);
		one.addCard(stone);
		Player[] playerAr = { one, two, three };

		MainFrame f = new MainFrame("Seven Wonders");
		f.setupGraphics(playerAr);
		//f.updateCurrentAge(2);
		//f.updateCurrentPlayer(two);
		//f.updatePlayerCoins();

	}
}
