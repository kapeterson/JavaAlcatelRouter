package router.alcatel.router.policy;


import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.*;

/** 
 * Models a Policy Statement in the 7x50 **/

public class SRPolicyStatement extends AlcatelObject {
	

	public SRPolicyStatement(String communityName){
		super(AlcatelObjectType.POLICYSTATEMENT);
		this.setName(communityName);
		

	}

	
}
