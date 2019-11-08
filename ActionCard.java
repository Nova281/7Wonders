import java.util.ArrayList;

public class ActionCard extends Card{
	private String effect;
	
	public ActionCard(String name,int age,ArrayList<String> cost,String color,ArrayList<String> chain, String freeCard, String effect)
	{
		super(name,age,cost,color,chain,freeCard);
		setEffect(effect);
	}
	private void setEffect(String effect)
	{
		this.effect = effect;
	}
	public String getEffect()
	{
		return effect;
	}
}
