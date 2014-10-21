package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models the Forwarding Path Ingress for IOM and MDA. It is 
 * @author kp109p
 *
 */
public class SRFPIngress extends AlcatelObject {
	
	/** IMPM section for the MDA.  Will be null for IOM3 and IMM **/
	public SRMcastPathManagement PATHMGMT = null;
	
	
	public SRFPIngress(AlcatelObject parent){
		super(AlcatelObjectType.FPINGRESS);
		this.parent = parent;
		
		
		//SRIOMObject iom = null;
		if ( parent.isMDAObject()) {
			//iom = (SRIOMObject)parent.getParent();
		} else if ( parent.isIOMObject()){
			//iom = (SRIOMObject)parent;
		} else if ( parent.isIOMFP()){
			//iom = (SRIOMObject)parent.getParent();
		
		} else {
			System.out.println("Invalid parent type when assigning forwarding patch to parent " + parent.getObjectType());
			System.exit(1);
		}
		
			PATHMGMT = new SRMcastPathManagement();
	}
	

	@Override
	public boolean isMDAIngressObject(){
		return true;
	}
	

}
