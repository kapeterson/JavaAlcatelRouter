package router.alcatel.router.service;

public class SRSDPObject extends SRServiceObject{
	
	public SRSDPObject(Integer sdpnumber){
		super(sdpnumber);
	
	}
	
	public boolean isSDPObject(){
		return true;
	}
}
