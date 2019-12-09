

import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;



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
		ResourceCard stone = new ResourceCard("StonePit;brown;1; ; ; ");
		//ActionCard worker = new ActionCard("WorkersGuild;purple;3;ore, ore, clay, stone, wood; ; ");
		RedCard barracks = new RedCard("Barracks;red;1;ore; ; ");
		one.addCard(altar);
		one.addCard(academy);
		one.addCard(palace);
		one.addCard(pantheon);
		one.addCard(stone);
		one.addCard(barracks);
		//one.addCard(worker);
		Player[] playerAr = { one, two, three };
		
		ArrayList<Card> test = new ArrayList<Card>();
		test.add(altar);
		test.add(academy);
		test.add(pantheon);
		test.add(pantheon);
		test.add(pantheon);
		test.add(pantheon);
		test.add(pantheon);
		
		LinkedHashMap<Player, ArrayList<Card>> playerMap = new LinkedHashMap<>();
		playerMap.put(one, test);
		MainFrame f = new MainFrame("Seven Wonders");
		f.setupGraphics(playerAr, playerMap);
		//f.updateCurrentAge(2);
		//f.updateCurrentPlayer(two);
		//f.updatePlayerCoins();

	}
}
