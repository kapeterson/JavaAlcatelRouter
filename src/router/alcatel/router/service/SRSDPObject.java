package router.alcatel.router.service;

import router.alcatel.router.AlcatelObjectType;

public class SRSDPObject extends SRServiceObject{
	
	public SRSDPObject(Integer sdpnumber){
		super(sdpnumber);
		this.setName(sdpnumber.toString());
		this.setObjectType(AlcatelObjectType.SDPOBJECT);
	
	}
	
	public boolean isSDPObject(){
		return true;
	}
}
