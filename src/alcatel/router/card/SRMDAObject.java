package alcatel.router.card;

import alcatel.router.AlcatelHardwareObject;

public class SRMDAObject extends AlcatelHardwareObject{

	public SRMDAObject(String mdatype){
		mdaType = mdatype;
	}
	
	private String mdaType = "";

	
	public String getMDAType(){
		return this.mdaType;
	}
}
