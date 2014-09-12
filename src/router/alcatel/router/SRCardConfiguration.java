package router.alcatel.router;
import java.util.TreeMap;
import java.util.Hashtable;

import router.alcatel.router.card.SRCardObject;

public class SRCardConfiguration {
	
	protected TreeMap<Integer, SRCardObject> cards = null;
    protected Hashtable<String, String> cardTypes = null;
    protected Hashtable<String, String> mdaTypes = null;
    
	//protected Hashtable<String, AlcatelHardwareObject> hardwareIndexMap = null;
	
    public SRCardConfiguration(){
		cards = new TreeMap<Integer, SRCardObject>();
		cardTypes = new Hashtable<String, String>();
		mdaTypes = new Hashtable<String, String>();
		
		//hardwareIndexMap = new Hashtable<String, AlcatelHardwareObject>();
	}
	
    /*
    public boolean hasHardwareIndex(String indx){
    	return hardwareIndexMap.containsKey(indx);
    }
    
    public AlcatelHardwareObject getHardwareByIndex(String indx){
    	return hardwareIndexMap.get(indx);
    }
    public void addIndexMap(String indx, AlcatelHardwareObject hw){
    		hardwareIndexMap.put(indx, hw);
    }
    */
    
	public String getCardTypeByIndex(String indx){
		return cardTypes.get(indx);
	}
	
	public void setCardTypes(Hashtable<String, String> cardtypes){
		cardTypes = cardtypes;
	}
	
	public void setMDATypes(Hashtable<String, String> mdatypes){
		this.mdaTypes = mdatypes;
	}
	
	public String getMDATypeByIndex(String indx){
		return mdaTypes.get(indx);
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