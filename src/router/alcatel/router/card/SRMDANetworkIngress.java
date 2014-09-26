package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SRMDANetworkIngress extends AlcatelObject {
	
	protected SRNetworkQueueQOSPolicy ingressqueue = null;
	
	
	public SRMDANetworkIngress(){
		super(AlcatelObjectType.MDANETWORKINGRESS);

	}
	
	
	public void setIngressQOS(SRNetworkQueueQOSPolicy policy){
		this.ingressqueue = policy;
	}
	
	public SRNetworkQueueQOSPolicy getIngressQueuePolicy(){
		return this.ingressqueue;
	}
	
	public String getIngressQueuePolicyName(){
		if ( this.ingressqueue == null )
			return "default";
		else
			return this.ingressqueue.getName();
	}
	
	
}
