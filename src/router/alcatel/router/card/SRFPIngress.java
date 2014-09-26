package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.impm.SRBandwidthPolicy;
import router.alcatel.router.qos.SRNetworkQueueQOSPolicy;

public class SRFPIngress extends AlcatelObject {
	
	/** IMPM section for the MDA.  Will be null for IOM3 and IMM **/
	public SRMcastPathManagement PATHMGMT = null;
	
	
	public SRFPIngress(AlcatelObject parent){
		super(AlcatelObjectType.FPINGRESS);
		this.parent = parent;
		
		SRIOMObject iom = null;
		if ( parent.isMDAObject()) {
			iom = (SRIOMObject)parent.getParent();
		} else if ( parent.isIOMObject()){
			iom = (SRIOMObject)parent;
		}
		
			PATHMGMT = new SRMcastPathManagement();
	}
	

	@Override
	public boolean isMDAIngressObject(){
		return true;
	}
	

}
