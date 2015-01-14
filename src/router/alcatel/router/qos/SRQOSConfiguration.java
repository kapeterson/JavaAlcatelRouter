package router.alcatel.router.qos;

import java.util.TreeMap;

/**
 * Models QOS Configuration on the 7x50
 * @author Kris Peterson
 *
 */
public class SRQOSConfiguration {
	
	/** Map of the Network QOS Policies **/
	protected TreeMap<Integer, SRNetworkQOSPolicy> networkPolicies = null;
	
	/** Map of the SAP Ingress QOS Policies **/
	protected TreeMap<Integer, SAPIngressQOSPolicy> sapIngressPolicies = null;
	
	/** Map of the sap egress qos policies **/
	protected TreeMap<Integer, SAPEgressQOSPolicy> sapEgressPolicies = null;
	
	/** Map of the Network Queue QOS Policies **/
	protected TreeMap<String, SRNetworkQueueQOSPolicy> networkQueuePolicies = null;

	
	public SRQOSConfiguration(){
		
		networkPolicies = new TreeMap<Integer, SRNetworkQOSPolicy>();
		this.createNetworkQos();
		
		sapIngressPolicies = new TreeMap<Integer, SAPIngressQOSPolicy>();
		sapEgressPolicies = new TreeMap<Integer, SAPEgressQOSPolicy>();
		networkQueuePolicies = new TreeMap<String, SRNetworkQueueQOSPolicy>();
	}
	
	
	/** Creates the default network qos policy **/
	public void createNetworkQos(){
		this.addNetworkQOSPolicy(new SRNetworkQOSPolicy(1));
	}
	
	/** Add the network qos policy to the qos configuration **/
	public void addNetworkQOSPolicy(SRNetworkQOSPolicy policy){
		this.networkPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	
	/** Add the nework queue QOS policy to the QOS configuration **/
	public void addNetworkQueueQOSPolicy(SRNetworkQueueQOSPolicy policy){
		this.networkQueuePolicies.put(policy.getPolicyName(), policy);
	}
	
	
	/** Add the SAP Ingress QOS Policy to the QOS configuration **/
	public void addSAPIngressQOSPolicy(SAPIngressQOSPolicy policy){
		this.sapIngressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	/** Add the SAP Egress QOS Policy to the QOS configuration **/
	public void addSAPEgressQOSPolicy(SAPEgressQOSPolicy policy){
		this.sapEgressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	/** Get the Network QOS policy with the provided policy number **/
	public SRNetworkQOSPolicy getNetworkQOSPolicy(Integer policynumber){
		return this.networkPolicies.get(policynumber);
	}
	
	/** Get the Network Queue QOS Policy with the provided policy name **/
	public SRNetworkQueueQOSPolicy getNetworkQueueQOSPolicy(String policyName){
		return this.networkQueuePolicies.get(policyName);
	}
	
	/** Get the SAP Ingress QOS Policy with the provided policy number **/
	public SAPIngressQOSPolicy getSAPIngressQOSPolicy(Integer policyNumber){
		return this.sapIngressPolicies.get(policyNumber);
	}
	
	
	/** Get the SAP Egress QOS Policy with the provided policy number **/
	public SAPEgressQOSPolicy getSAPEgressQOSPolicy(Integer policyNumber){
		return this.sapEgressPolicies.get(policyNumber);
	}
	
	/** Get the full map of SAP Egress QOS Policies **/
	public TreeMap<Integer, SAPEgressQOSPolicy> getSAPEgressQOSPolicies(){
		return this.sapEgressPolicies;
	}
	
	/** Get the full map of SAP Ingress QOS Policies **/
	public TreeMap<Integer, SAPIngressQOSPolicy> getSAPIngressQOSPolicies(){
		return this.sapIngressPolicies;
	}
	
	/** Get the full map of Network QOS Policies **/
	public TreeMap<Integer, SRNetworkQOSPolicy> getNetworkQOSPolicies(){
		return this.networkPolicies;
	}
	
	/** Get the full map of network queue qos policies **/
	public TreeMap<String, SRNetworkQueueQOSPolicy> getNetworkQueueQOSPolicies(){
		return this.networkQueuePolicies;
	}
	
	public boolean hasSAPIngressPolicy(Integer policyNumber){
		
		return this.sapIngressPolicies.containsKey(policyNumber);
	}

	
}
