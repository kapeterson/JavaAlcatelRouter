package router.alcatel.router.impm;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;


/**
 * Models an IMPM Bandwidth policy
 * @author kp109p
 *
 */
public class SRBandwidthPolicy extends AlcatelObject {
	
	public SRBandwidthPolicy(String policyName){
		super(AlcatelObjectType.BANDWIDTHPOLICY);
		this.setName(policyName);
	}
	
	public boolean isBandwidthPolicy(){
		return true;
	}

}
