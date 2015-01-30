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
	
	TreeMap<String, ForwardingClass> forwardingClasses =  new TreeMap<String, ForwardingClass>();
	
	public SAPEgressQOSPolicy(){
		super(AlcatelObjectType.SAPEGRESSQOSPOLICY);
		
	}
	
	public void addFC(ForwardingClass fc){
		//System.out.println("Added fc");
		forwardingClasses.put(fc.getName(), fc);
		
	}
	
	public ForwardingClass getForwardingClass(String fcname){
		
		return this.forwardingClasses.get(fcname);
	}
	
	public boolean setForwardingClassQueue(String fcname, int queue){
		forwardingClasses.get(fcname).setQueue(queue);
		return true;
	}
	
	public TreeMap<String, ForwardingClass> getForwardingClasses(){
		return this.forwardingClasses;
	}
}
