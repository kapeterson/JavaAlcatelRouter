package router.alcatel.router.card;

import router.alcatel.router.AlcatelObjectType;

/**
 * Models CPM Object
 * @author Kris Peterson
 *
 */
public class SRCPMObject extends SRCardObject{
	
	public SRCPMObject(int cardNumber, String ctype){
		super(AlcatelObjectType.CPM, cardNumber, ctype);
	}
	
	public boolean isCPMObject(){
		return true;
	}
	
}
