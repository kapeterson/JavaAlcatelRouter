import java.util.TreeMap;
import java.util.Hashtable;

public class SRCardConfiguration {
	
	protected TreeMap<Integer, SRCardObject> cards = null;
    protected Hashtable<String, String> cardTypes = null;
	public SRCardConfiguration(){
		cards = new TreeMap<Integer, SRCardObject>();
		cardTypes = new Hashtable<String, String>();
	}
	
	public String getCardTypeByIndex(String indx){
		return cardTypes.get(indx);
	}
	public void setCardTypes(Hashtable<String, String> cardtypes){
		cardTypes = cardtypes;
	}
	
	public void addCard(int slotNumber, SRCardObject cardObject){
		
		cards.put(slotNumber, cardObject);
	}
	
	public SRCardObject getCard(String slotNumber){
		return cards.get(slotNumber);
	}
	
	public SRCardObject getCard(int slotNumber){
		return cards.get(slotNumber);
	}
	
	public TreeMap<Integer, SRCardObject> getCards(){
		return cards;
	}
}
