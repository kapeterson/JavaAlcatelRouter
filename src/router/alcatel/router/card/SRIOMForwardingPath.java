package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRIOMForwardingPath extends AlcatelObject {

	public SRMcastPathManagement PATHMGMT = new SRMcastPathManagement();
	 
	public SRIOMForwardingPath(){
		super(AlcatelObjectType.IOMFP);
		
	}
	
	
	@Override
	public boolean isForwardingPath(){
		return true;
	}
	
	public class ForwardingPathIngress {
		
		public ForwardingPathIngress(){
			
		}
	}
}
