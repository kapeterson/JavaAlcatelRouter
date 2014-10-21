package router.alcatel.router.service;


import router.alcatel.router.*;

/**
 * Models a Service SDP Object configured in a service (vpls, ies interface etc )
 * Base class from which mesh and spoke sdp are derived
 * @author Kris Peterson
 *
 */
public class SRServiceSDPObject extends AlcatelObject implements SRInterfaceBindingObject, SRServiceChild {

	/** SDP Number **/
	protected Integer sdpNumber = 0;
	
	/** Configured vc id **/
	protected Integer vcid = 0;
	
	/** Parent service object **/
	protected AlcatelObject parentService = null;
	
	/** Access the SDP Ingress configuration **/
	public SRSDPIngress INGRESS = new SRSDPIngress();
	
	/** Access the SDP Egress configuration **/
	public SRSDPEgress EGRESS = new SRSDPEgress();
	
	/** Acess the IGMP Snooping on the service sdp **/
	public SRIGMPSnooping IGMPSNOOPING = new SRIGMPSnooping();

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
	
	/** Get the name of the service sdp.  will be format sdp:vcid **/
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

	/** Get the parent service object of the service sdp **/
	public AlcatelObject getParentService(){
		return this.parentService;
	}

}
