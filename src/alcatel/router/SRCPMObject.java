package alcatel.router;

public class SRCPMObject extends SRCardObject{
	
	public SRCPMObject(int cardNumber, String ctype){
		super(cardNumber, ctype);
	}
	
	public boolean isCPMObject(){
		return true;
	}
	
}
