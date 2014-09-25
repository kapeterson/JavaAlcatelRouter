package router.alcatel.router.card;
import java.util.TreeMap;
import java.util.Hashtable;


/**
 * Exposes the card configuration section of the router.  Can then get lists
 * of cards, and access the MDA, CPM and any configured parameters.
 * @author pete
 *
 */
public class SRCardConfiguration {
	
	/** Hashtable of the cards.  key = cardNumber value=SRCardObject **/
	protected TreeMap<Integer, SRCardObject> cards = null;
	
	/** Hashtable of cardTypes.  Used when populating through SNMP.  Can lookup actual text card type by index **/
    protected Hashtable<String, String> cardTypes = null;
    
    /** Hashtable of mdaTypes.  Used when populating through SNMP>  Can lookup actual text mda type by index **/
    
    protected Hashtable<String, String> mdaTypes = null;
    
	//protected Hashtable<String, AlcatelHardwareObject> hardwareIndexMap = null;
	
    public SRCardConfiguration(){
		cards = new TreeMap<Integer, SRCardObject>();
		cardTypes = new Hashtable<String, String>();
		mdaTypes = new Hashtable<String, String>();
		
		//hardwareIndexMap = new Hashtable<String, AlcatelHardwareObject>();
	}
	

    /**
     * Gets the card type when supplied with the card type index from SNMP
     * @param indx Index of the card-type that ws populated through snmp
     * @return Returns the text value of the card-type
     */
	public String getCardTypeByIndex(String indx){
		return cardTypes.get(indx);
	}
	
	/**
	 * set the value of the card type hash
	 * @param cardtypes Hashtable that contains the index and card type
	 */
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
