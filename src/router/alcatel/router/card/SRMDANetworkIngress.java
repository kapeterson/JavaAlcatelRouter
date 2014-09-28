package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SRMDANetworkIngress extends AlcatelObject {
	
	//protected SRNetworkQueueQOSPolicy ingressqueue = null;
	protected String ingressNewtworkQueueName = "default";
	
	public SRMDANetworkIngress(){
		super(AlcatelObjectType.MDANETWORKINGRESS);

	}
	
	
	public void setIngressQOSName(String policy){
		this.ingressNewtworkQueueName = policy;
	}
	

	
	public String getIngressQueuePolicyName(){
		return this.ingressNewtworkQueueName;
	}
	
	
}
