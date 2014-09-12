package router.alcatel.router.card;

/**
 * Models CPM Object
 * @author Kris Peterson
 *
 */
public class SRCPMObject extends SRCardObject{
	
	public SRCPMObject(int cardNumber, String ctype){
		super(cardNumber, ctype);
	}
	
	public boolean isCPMObject(){
		return true;
	}
	
}
