package alcatel.router;

public class SRCardObject extends AlcatelHardwareObject{
	
	protected String cardType = "EMPTY";
	protected int slot = 0;
    protected SRMDAObject[] mdas;
    
	public SRCardObject(int cardNumber, String ctype){
		slot = cardNumber;
		cardType = ctype; 
		mdas = new SRMDAObject[2];
		mdas[0] = null;
		mdas[1] = null;
		
		//this.setObjectName("Card " + String.valueOf(cardNumber));
	}
	
	public void setMDA(int complex, SRMDAObject mda){
		mdas[complex-1] = mda;
	}
	
	public SRMDAObject getMDA(int complex){
		return mdas[complex-1];
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
}
