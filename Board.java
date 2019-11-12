import java.io.BufferedReader;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Board {
	
	private ArrayList<Card> deck = new ArrayList<>();
	
	public Board() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cards.txt"));
		StringTokenizer st, i;
		while(br.ready()) {
			st = new StringTokenizer(br.readLine(), ";");
			String name = st.nextToken();
			String color = st.nextToken();
			if(color.equals("brown") || color.equals("silver") || name.equals("caravansery") || name.equals("Forum"))
				deck.add(new ResourceCard(name, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("blue"))
				deck.add(new BlueCard(name, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("red"))
				deck.add(new RedCard(name, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("green"))
				deck.add(new GreenCard(name, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
			else
				deck.add(new ActionCard(name, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
		}
	}
}
