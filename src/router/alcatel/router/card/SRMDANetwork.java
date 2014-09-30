package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/** Models the MDA Network configuration **/
public class SRMDANetwork extends AlcatelObject{

	/** Accessor to the MDA INgress configuration **/
	public SRMDANetworkIngress INGRESS = new SRMDANetworkIngress();
	
	public SRMDANetwork(AlcatelObject parent){
		super(AlcatelObjectType.MDANETWORK);
		this.setParent(parent);

	}
	
	public boolean isMDANetworkObject(){
		return true;
	}
}
