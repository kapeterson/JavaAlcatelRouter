package router.alcatel.router.service;

import router.alcatel.router.*;
public class SRServiceSDPObject extends AlcatelObject{

	protected Integer sdpNumber = 0;
	protected Integer vcid = 0;
	
	public SRServiceSDPObject(AlcatelObjectType sdpType, String sdpName){
		super(sdpType);
		String[] sdp = sdpName.split(":");
		this.sdpNumber = Integer.parseInt(sdp[0]);
		this.vcid = Integer.parseInt(sdp[1]);
		
	}
	
	public String getSDPName(){
		return sdpNumber + ":" + vcid;
	}
}
