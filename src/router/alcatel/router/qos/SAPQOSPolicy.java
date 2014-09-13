package router.alcatel.router.qos;

import router.alcatel.router.*;


public abstract class SAPQOSPolicy extends AlcatelObject{
	
	protected Integer policyNumber = 0;
	public SAPQOSPolicy(AlcatelObjectType qosType){
		super(qosType);
		this.policyNumber = 0;
	}
	
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
}
