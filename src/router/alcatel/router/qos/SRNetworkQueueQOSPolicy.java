package router.alcatel.router.qos;

import router.alcatel.router.*;

/**
 * Models a Network Queue QOS Policy on the 7x50
 * @author Kris Peterson
 *
 */
public class SRNetworkQueueQOSPolicy extends AlcatelObject {
	
	protected String policyName = "";
	
	public SRNetworkQueueQOSPolicy(){
		super(AlcatelObjectType.NETWORKQUEUEQOSPOLICY);
		this.policyName = "";
	}
	
	/**
	 * Get the policy name
	 * @return String of the policy name
	 */
	public String getPolicyName(){
		return this.policyName;
		
	}
	
	
	/**
	 * Sets the name of the Network Queue QOS Policy
	 * @param policyname
	 */
	public void setPolicyName(String policyname){
		this.policyName = policyname;
		this.setName(policyname);
	}
}
