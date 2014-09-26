package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.impm.SRBandwidthPolicy;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SRMDAIngress extends AlcatelObject {
	
	/** IMPM section for the MDA.  Will be null for IOM3 and IMM **/
	public SRMDAMcastPathManagement MDAPATHMGMT = null;
	
	
	public SRMDAIngress(SRMDAObject mda){
		super(AlcatelObjectType.MDAINGRESS);
		this.parent = mda;
		
		SRIOMObject iom = (SRIOMObject)mda.getParent();
		if ( iom.isIOMb()){
			MDAPATHMGMT = new SRMDAMcastPathManagement();
		}
	}
	

	@Override
	public boolean isMDAIngressObject(){
		return true;
	}
	

}
