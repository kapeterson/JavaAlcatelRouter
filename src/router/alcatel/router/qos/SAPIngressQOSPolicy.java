package router.alcatel.router.qos;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObjectType;

/**
 * Models a SAP Ingress QOS Policy
 * @author Kris Peterson
 *
 */
public class SAPIngressQOSPolicy extends SAPQOSPolicy {
	TreeMap<String, ForwardingClass> forwardingClasses =  new TreeMap<String, ForwardingClass>();

	public SAPIngressQOSPolicy(){
		super(AlcatelObjectType.SAPINGRESSQOSPOLICY);
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
	

	/** Does the ingress policy have the forwarding class configured **/
	public boolean hasForwardingClass(String fcname){
		
		return this.forwardingClasses.containsKey(fcname);
	}
	/** Get a map of all the forwarding classes **/
	public TreeMap<String, ForwardingClass> getForwardingClasses(){
		return this.forwardingClasses;
	}
}
