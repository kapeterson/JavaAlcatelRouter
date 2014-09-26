package router.alcatel.router.port;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SREthernetNetwork extends AlcatelObject {
	
	protected SRNetworkQueueQOSPolicy queuepolicy = null;

	public SREthernetNetwork(){
		super(AlcatelObjectType.ETHERNETNETWORK);
	}
	
	public void setQueuePolicy(SRNetworkQueueQOSPolicy policy){
		this.queuepolicy = policy;
	}
	
	public SRNetworkQueueQOSPolicy getQueuePolicy(){
		return this.queuepolicy;
	}
	
	public String getQueuePolicyName(){
		if ( this.queuepolicy == null )
			return "default";
		else
			return this.queuepolicy.getName();
	}
}
