package router.alcatel.router.card;

import router.alcatel.router.AlcatelHardwareObject;

public class SRCardObject extends AlcatelHardwareObject{
	
	protected String cardType = "EMPTY";
	protected int slot = 0;
    protected SRMDAObject[] mdas;
    
    
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
	
	
	
	public void setSlotNumber(byte slt){
		slot = slt;
	}
	
	public int getSlotNumber(){
		return slot;
	}
	
	public String getCardType(){
		return cardType;
	}
	
	public void setCardType(String cardType){
		this.cardType = cardType;
	}
}