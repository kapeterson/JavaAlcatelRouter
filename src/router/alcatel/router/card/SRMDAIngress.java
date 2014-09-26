package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.impm.SRBandwidthPolicy;

public class SRMDAIngress extends AlcatelObject {
	
	/** IMPM section for the MDA.  Will be null for IOM3 and IMM **/
	public MDAMcastPathManagement MDAPATHMGMT = null;
	
	public SRMDAIngress(SRMDAObject mda){
		super(AlcatelObjectType.MDAINGRESS);
		this.parent = mda;
		
		SRIOMObject iom = (SRIOMObject)mda.getParent();
		if ( iom.isIOMb()){
			MDAPATHMGMT = new MDAMcastPathManagement();
		}
	}
	
	
	public class MDAMcastPathManagement extends AlcatelObject {
		
		protected SRBandwidthPolicy bwPolicy = null;
		
		public MDAMcastPathManagement(){
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
		
	}

}
