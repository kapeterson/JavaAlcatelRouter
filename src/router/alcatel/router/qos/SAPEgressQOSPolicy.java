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
	
	
	/** Add a forwarding class to the policy **/
	public void addFC(ForwardingClass fc){
		//System.out.println("Added fc");
		forwardingClasses.put(fc.getName(), fc);
		
	}
	
	/** Get the requsted forwarding class **/
	public ForwardingClass getForwardingClass(String fcname){
		
		return this.forwardingClasses.get(fcname);
	}
	

	public boolean hasForwardingClass(String fcname){
		
		return this.forwardingClasses.containsKey(fcname);
	}
	/** Get a map of all the forwarding classes **/
	public TreeMap<String, ForwardingClass> getForwardingClasses(){
		return this.forwardingClasses;
	}
}
