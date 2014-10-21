package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models the MDA Network Ingress Configuration
 * @author Kris Peterson
 *
 */
public class SRMDANetworkIngress extends AlcatelObject {
	
	//protected SRNetworkQueueQOSPolicy ingressqueue = null;
	protected String ingressNewtworkQueueName = "default";
	
	public SRMDANetworkIngress(){
		super(AlcatelObjectType.MDANETWORKINGRESS);

	}
	
	
	/**
	 * Sets the name of the QOS Policy applied to the MDA
	 * @param policy
	 */
	public void setIngressQOSName(String policy){
		this.ingressNewtworkQueueName = policy;
	}
	

	/**
	 * Gets the name of the QOS policy applied to the MDA
	 * @return String name of the qos policy
	 */
	public String getIngressQueuePolicyName(){
		return this.ingressNewtworkQueueName;
	}
	
	
}
