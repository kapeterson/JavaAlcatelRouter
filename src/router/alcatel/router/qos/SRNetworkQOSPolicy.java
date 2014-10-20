package router.alcatel.router.qos;

import router.alcatel.router.*;


/**
 * Models a Network QOS policy on the 7x50
 * @author Kris Peterson
 *
 */
public class SRNetworkQOSPolicy extends AlcatelObject {
	
	protected Integer policyNumber = 0;
	
	public SRNetworkQOSPolicy(Integer policynumber){
		super(AlcatelObjectType.NETWORKQOSPOLICY);
		this.policyNumber = policynumber;
	}
	
	
	/**
	 * Set the policy number
	 * @param policynumber 
	 */
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	
	/**
	 * get the number of the Network QOS Policy 
	 * @return number of the policy
	 */
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
	

}
