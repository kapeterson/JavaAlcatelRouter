package router.alcatel.router.qos;

import java.util.TreeMap;

import router.alcatel.router.*;

/**
 * Base SAP QOS Policy - SAP Ingress & Egress will extend it
 * @author Kris Peterson
 *
 */
public abstract class SAPQOSPolicy extends AlcatelObject{
	TreeMap<Integer, SRSAPQueue> queues = new TreeMap<Integer, SRSAPQueue>();
	TreeMap<String, ForwardingClass> forwardingClasses =  new TreeMap<String, ForwardingClass>();

	protected Integer policyNumber = 0;

	public SAPQOSPolicy(AlcatelObjectType qosType){
		super(qosType);
		this.policyNumber = 0;
	}
	
	/** set the policy number **/
	public void setPolicyNumber(Integer policynumber){
		this.policyNumber = policynumber;
	}
	
	
	/** get the policy number **/
	public Integer getPolicyNumber(){
		return this.policyNumber;
	}
	
	
	/** add a queue to the sap policy **/
	public void addQueue(SRSAPQueue queue){
		this.queues.put(queue.getQueueNumber(), queue);
	}
	
	/** get the treemap of queues **/
	public TreeMap<Integer, SRSAPQueue> getQueues(){
		return this.queues;
	}
	
	public SRSAPQueue getQueue(Integer queueNumber){
		return this.queues.get(queueNumber);
	}
	
	public boolean hasQueue(Integer queueNumber){
		return this.queues.containsKey(queueNumber);
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
