package router.alcatel.router.port;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SREthernetNetwork extends AlcatelObject {
	
	protected String networkQueuePolicyName = "default";
	
	public SREthernetNetwork(){
		super(AlcatelObjectType.ETHERNETNETWORK);
	}
	
	public void setQueuePolicyName(String policyName){
		this.networkQueuePolicyName = policyName;
	}
	

	public String getQueuePolicyName(){
			return this.networkQueuePolicyName;
	}
}
