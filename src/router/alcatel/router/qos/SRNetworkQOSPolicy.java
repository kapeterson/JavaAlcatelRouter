package router.alcatel.router.qos;

import router.alcatel.router.*;

public class SRNetworkQOSPolicy extends AlcatelObject {
	
	protected Integer policyNumber = 0;
	
	public SRNetworkQOSPolicy(Integer policynumber){
		super(AlcatelObjectType.NETWORKQOSPOLICY);
		this.policyNumber = policynumber;
	}
	
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
	

}
