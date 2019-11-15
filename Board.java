import java.io.BufferedReader;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Board {
	
	private ArrayList<Card> age1 = new ArrayList<>();
	private ArrayList<Card> age2 = new ArrayList<>();
	private ArrayList<Card> age3 = new ArrayList<>();
	private ArrayList<ArrayList<Card>> ages;
	public Board() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cards.txt"));
		StringTokenizer st, i;
		age1 = new ArrayList<>();
		age2 = new ArrayList<>();
		age3 = new ArrayList<>();
		ages = new ArrayList<>();
		
		ages.add(age1);
		ages.add(age2);
		ages.add(age3);
		
		while(br.ready()) {
			st = new StringTokenizer(br.readLine(), ";");
			String name = st.nextToken();
			String color = st.nextToken();
			String age = st.nextToken();
			if(color.equals("brown") || color.equals("silver") || name.equals("caravansery") || name.equals("Forum"))
				ages.get(Integer.parseInt(age) - 1).add(new ResourceCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("blue"))
				ages.get(Integer.parseInt(age) - 1).add(new BlueCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("red"))
				ages.get(Integer.parseInt(age) - 1).add(new RedCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else if(color.equals("green"))
				ages.get(Integer.parseInt(age) - 1).add(new GreenCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			else {
				ages.get(Integer.parseInt(age) - 1).add(new ActionCard(name, color, age, st.nextToken(), st.nextToken(), st.nextToken()));
			}
			
		}
	}
	
	public void printDeck() {
		age1.sort(null);
		age2.sort(null);
		age3.sort(null);
		System.out.println("Age 1");
		for(Card c: age1)
			System.out.println(c);
		System.out.println("----------------------------\nAge 2");
		for(Card c: age2)
			System.out.println(c);
		System.out.println("----------------------------\nAge 3");
		for(Card c: age3)
			System.out.println(c);
	}
	
}
