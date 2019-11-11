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
			String age = st.nextToken();
			String color = st.nextToken();
			deck.add(new Card(name, age, color, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
		}
	}
}
