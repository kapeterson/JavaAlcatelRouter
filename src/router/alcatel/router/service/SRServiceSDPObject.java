package router.alcatel.router.service;

import java.util.ArrayList;

import router.alcatel.router.*;

public class SRServiceSDPObject extends AlcatelObject implements SRInterfaceBindingObject, SRServiceChild {

	protected Integer sdpNumber = 0;
	protected Integer vcid = 0;
	protected AlcatelObject parentService = null;
	public SRSDPIngress INGRESS = new SRSDPIngress();
	public SRSDPEgress EGRESS = new SRSDPEgress();
	
	
	public SRServiceSDPObject(AlcatelObjectType sdpType, String sdpName, AlcatelObject parentSDP){
		super(sdpType);
		
		
		// verify object type
		if ( sdpType != null && ( sdpType != AlcatelObjectType.MESHSDPOBJECT && sdpType != AlcatelObjectType.SPOKESDPOBJECT))
			System.out.println("ERROR: Invalid object type in sdp instantiation " + sdpType);
		
		
		if ( sdpType != null)
			this.setObjectType(sdpType);
		else
			this.setObjectType(AlcatelObjectType.SERVICESDPOBJECT);
		
		this.setName(sdpName);
		String[] sdp = sdpName.split(":");
		this.sdpNumber = Integer.parseInt(sdp[0]);
		this.vcid = Integer.parseInt(sdp[1]);
		this.parent = parentSDP;
		
	}
	
	public String getSDPName(){
		return sdpNumber + ":" + vcid;
	}
	


	@Override
	public boolean isServiceSDPObject(){
		return true;
	}
	
	@Override
	public boolean isBindingChild(){
		return true;
	}

	public AlcatelObject getParentService(){
		return this.parentService;
	}

}
