package router.alcatel.router.impm;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRBandwidthPolicy extends AlcatelObject {
	
	public SRBandwidthPolicy(String policyName){
		super(AlcatelObjectType.BANDWIDTHPOLICY);
		this.setName(policyName);
	}

}
