package router.alcatel.router.card;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models the IOM Forwarding Path
 * @author Kris Peterson
 *
 */
public class SRIOMForwardingPath extends AlcatelObject {

	/** Accessor to the Forwarding Path Ingress configuration **/
	public SRFPIngress INGRESS = null;
	
	public SRIOMForwardingPath(){
		super(AlcatelObjectType.IOMFP);
		INGRESS = new SRFPIngress(this);
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
