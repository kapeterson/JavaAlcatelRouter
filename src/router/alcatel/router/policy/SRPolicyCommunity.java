package router.alcatel.router.policy;

import router.alcatel.router.*;


public class SRPolicyCommunity extends AlcatelObject {
	

	public SRPolicyCommunity(String communityName){
		super(AlcatelObjectType.POLICYCOMMUNITY);
		this.setName(communityName);
		

	}
	
	
	
}
