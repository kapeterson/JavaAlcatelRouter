package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRMDANetwork extends AlcatelObject{

	public SRMDANetworkIngress INGRESS = new SRMDANetworkIngress();
	
	public SRMDANetwork(AlcatelObject parent){
		super(AlcatelObjectType.MDANETWORK);
		this.setParent(parent);

	}
	
	public boolean isMDANetworkObject(){
		return true;
	}
}
