package router.alcatel.router.card;

import router.alcatel.router.AlcatelHardwareObject;

/**
 * Base CArd Model from which IOM and CPM will extend
 * @author Kris Peterson
 *
 */
public class SRCardObject extends AlcatelHardwareObject{
	/** Card Type value **/
	
	protected String cardType = "EMPTY";
	
	/** Slot in which teh IOM resides relative 1 - 0 is invalid **/
	protected int slot = 0;
	
	/** Arary of MDAs.  Will be initialized to 2 for each complex **/
    protected SRMDAObject[] mdas;
    
    /**
     * Default constructor for CardObject
     */
    
    public SRCardObject(){
    	this.slot = 0;
    	this.cardType = "EMPTY";
    }
    
    public SRCardObject(int cardNumber){
    	this.slot = cardNumber;
    	this.cardType = "EMPTY";
    }
    
	public SRCardObject(int cardNumber, String ctype){
		slot = cardNumber;
		cardType = ctype; 

		
		//this.setObjectName("Card " + String.valueOf(cardNumber));
	}
	
	
	/**
	 * Sets the slot number of the Card
	 * @param slt slot number integer relative 1.  Card 1 will have slot 1
	 */
	public void setSlotNumber(byte slt){
		slot = slt;
	}
	
	/**
	 * Returns teh slot of the card
	 * @return integer giving the slot number of the card relative 1. First card = 1.  0 = invalid
	 */
	public int getSlotNumber(){
		return slot;
	}
	
	/**
	 * Returns the card type
	 * @return string card type
	 */
	public String getCardType(){
		return cardType;
	}
	
	/**
	 * sets the card type
	 * @param cardType string value of the card type.  unchecked / unverified.
	 */
	public void setCardType(String cardType){
		this.cardType = cardType;
	}
}
