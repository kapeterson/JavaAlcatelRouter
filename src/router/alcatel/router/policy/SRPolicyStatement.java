package router.alcatel.router.policy;


import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.*;

public class SRPolicyStatement extends AlcatelObject {
	

	public SRPolicyStatement(String communityName){
		super(AlcatelObjectType.POLICYSTATEMENT);
		this.setName(communityName);
		

	}

	
}
