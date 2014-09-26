package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.impm.SRBandwidthPolicy;


public class SRMDAMcastPathManagement extends AlcatelObject {
	
	protected SRBandwidthPolicy bwPolicy = null;
	
	public SRMDAMcastPathManagement(){
		super(AlcatelObjectType.MDAMCASTPATHMANAGEMENT);
	}
	
	public void setBandwidthPolicy(SRBandwidthPolicy bwPolicy){
		this.bwPolicy = bwPolicy;
	}
	
	public SRBandwidthPolicy getBandwidthPolicy(){
		return this.bwPolicy;
	}
	
	public String getBandwidthPolicyName(){
		
		if ( this.bwPolicy != null)
			return this.bwPolicy.getName();
		else
			return "";
	}
	
	public boolean isMDAPathManagement(){
		return true;
	}
	
}
