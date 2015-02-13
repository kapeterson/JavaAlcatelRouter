package router.alcatel.router.qos;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObjectType;

/**
 * Model a SAP Egress QOS Policy
 * @author Kris Peterson
 *
 */
public class SAPEgressQOSPolicy extends SAPQOSPolicy {
	String description = "";
	
	
	public SAPEgressQOSPolicy(){
		super(AlcatelObjectType.SAPEGRESSQOSPOLICY);
		
	}
	
	


}
