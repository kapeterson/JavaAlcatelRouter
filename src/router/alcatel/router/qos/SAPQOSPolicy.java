package router.alcatel.router.qos;

import router.alcatel.router.*;

/**
 * Base SAP QOS Policy - SAP Ingress & Egress will extend it
 * @author Kris Peterson
 *
 */
public abstract class SAPQOSPolicy extends AlcatelObject{
	
	protected Integer policyNumber = 0;
	public SAPQOSPolicy(AlcatelObjectType qosType){
		super(qosType);
		this.policyNumber = 0;
	}
	
	/** set the policy number **/
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	
	/** get the policy number **/
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
}
