package router.alcatel.router.qos;

import router.alcatel.router.*;

public class NetworkQOSPolicy extends AlcatelObject {
	
	protected Integer policyNumber = 0;
	
	public NetworkQOSPolicy(){
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
