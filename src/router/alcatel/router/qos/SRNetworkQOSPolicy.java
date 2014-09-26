package router.alcatel.router.qos;

import router.alcatel.router.*;

public class SRNetworkQOSPolicy extends AlcatelObject {
	
	protected Integer policyNumber = 0;
	
	public SRNetworkQOSPolicy(){
		super(AlcatelObjectType.NETWORKQOSPOLICY);
		this.policyNumber = 0;
	}
	
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
	

}
