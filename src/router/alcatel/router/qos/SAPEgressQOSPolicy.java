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
	
	TreeMap<String, ForwardingClass> fc =  new TreeMap<String, ForwardingClass>();
	
	public SAPEgressQOSPolicy(){
		super(AlcatelObjectType.SAPEGRESSQOSPOLICY);
		
	}
	
	public void addFC(String fcname){
		
		fc.put(fcname, new ForwardingClass());
		
	}
	
	
	public boolean setForwardingClassQueue(String fcname, int queue){
		fc.get(fcname).setQueue(queue);
		return true;
	}
	
	public TreeMap<String, ForwardingClass> getForwardingClasses(){
		return this.fc;
	}
}
