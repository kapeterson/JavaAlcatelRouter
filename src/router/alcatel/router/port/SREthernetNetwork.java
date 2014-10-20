package router.alcatel.router.port;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

/**
 * Models a port Ethernet Network component
 * @author Kris Peterson
 *
 */
public class SREthernetNetwork extends AlcatelObject {
	
	protected String networkQueuePolicyName = "default";
	
	public SREthernetNetwork(){
		super(AlcatelObjectType.ETHERNETNETWORK);
	}
	
	
	/**
	 * Set the name of the Queue Policy applied to the Port Ethernet Network
	 * @param policyName
	 */
	public void setQueuePolicyName(String policyName){
		this.networkQueuePolicyName = policyName;
	}
	

	/** Get the name of the Queue policy applied to the port ethernet network component **/
	public String getQueuePolicyName(){
			return this.networkQueuePolicyName;
	}
}
