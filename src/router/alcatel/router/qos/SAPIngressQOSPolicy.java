package router.alcatel.router.qos;

import router.alcatel.router.AlcatelObjectType;

/**
 * Models a SAP Ingress QOS Policy
 * @author Kris Peterson
 *
 */
public class SAPIngressQOSPolicy extends SAPQOSPolicy {
	
	public SAPIngressQOSPolicy(){
		super(AlcatelObjectType.SAPINGRESSQOSPOLICY);
	}
}
