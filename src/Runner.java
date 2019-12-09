package graphicClasses;

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;

import cardClasses.BlueCard;
import cardClasses.GreenCard;
import playerClasses.Player;
import wonderClasses.Alexandria;
import wonderClasses.Babylon;
import wonderClasses.Ephesus;
import wonderClasses.Gizah;
import wonderClasses.Halicarnassus;
import wonderClasses.Olympia;
import wonderClasses.Rhodos;
import wonderClasses.Wonder;

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
		one.addCoins(2);
		BlueCard altar = new BlueCard("Altar", "blue", "1", " ", "Temple", " ");
		BlueCard palace = new BlueCard("Palace;blue;3;glass, papyrus, cloth, clay, wood, ore, stone; ; ");
		BlueCard pantheon = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		BlueCard pantheon1 = new BlueCard("Pantheon;blue;3;clay, clay, ore, papyrus, cloth, glass; ;Temple");
		GreenCard academy = new GreenCard("Academy;green;3;stone, stone, stone, glass; ;School");
		one.addCard(altar);
		one.addCard(academy);
		one.addCard(palace);
		one.addCard(pantheon);
		one.addCard(pantheon1);
		two.addCoins(1);
		three.addCoins(5);
		Player[] playerAr = { one, two, three };

		MainFrame f = new MainFrame("Seven Wonders", playerAr);
		f.updateCurrentAge(2);
		f.updateCurrentPlayer(two);
		f.updatePlayerCoins();

	}
}
