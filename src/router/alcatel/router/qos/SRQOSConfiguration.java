package router.alcatel.router.qos;

import java.util.TreeMap;

public class SRQOSConfiguration {
	
	protected TreeMap<Integer, SRNetworkQOSPolicy> networkPolicies = null;
	protected TreeMap<Integer, SAPIngressQOSPolicy> sapIngressPolicies = null;
	protected TreeMap<Integer, SAPEgressQOSPolicy> sapEgressPolicies = null;
	protected TreeMap<String, SRNetworkQueueQOSPolicy> networkQueuePolicies = null;

	
	public SRQOSConfiguration(){
		
		networkPolicies = new TreeMap<Integer, SRNetworkQOSPolicy>();
		this.createNetworkQos1();
		
		sapIngressPolicies = new TreeMap<Integer, SAPIngressQOSPolicy>();
		sapEgressPolicies = new TreeMap<Integer, SAPEgressQOSPolicy>();
		networkQueuePolicies = new TreeMap<String, SRNetworkQueueQOSPolicy>();
	}
	
	
	public void createNetworkQos1(){
		this.addNetworkQOSPolicy(new SRNetworkQOSPolicy(1));
	}
	public void addNetworkQOSPolicy(SRNetworkQOSPolicy policy){
		this.networkPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public void addNetworkQueueQOSPolicy(SRNetworkQueueQOSPolicy policy){
		this.networkQueuePolicies.put(policy.getPolicyName(), policy);
	}
	
	public void addSAPIngressQOSPolicy(SAPIngressQOSPolicy policy){
		this.sapIngressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public void addSAPEgressQOSPolicy(SAPEgressQOSPolicy policy){
		this.sapEgressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public SRNetworkQOSPolicy getNetworkQOSPolicy(Integer policynumber){
		return this.networkPolicies.get(policynumber);
	}
	
	public SRNetworkQueueQOSPolicy getNetworkQueueQOSPolicy(String policyName){
		return this.networkQueuePolicies.get(policyName);
	}
	
	public SAPIngressQOSPolicy getSAPIngressQOSPolicy(Integer policyNumber){
		return this.sapIngressPolicies.get(policyNumber);
	}
	
	public SAPEgressQOSPolicy getSAPEgressQOSPolicy(Integer policyNumber){
		return this.sapEgressPolicies.get(policyNumber);
	}
	
	public TreeMap<Integer, SAPEgressQOSPolicy> getSAPEgressQOSPolicies(){
		return this.sapEgressPolicies;
	}
	
	public TreeMap<Integer, SAPIngressQOSPolicy> getSAPIngressQOSPolicies(){
		return this.sapIngressPolicies;
	}
	
	public TreeMap<Integer, SRNetworkQOSPolicy> getNetworkQOSPolicies(){
		return this.networkPolicies;
	}
	
	public TreeMap<String, SRNetworkQueueQOSPolicy> getNetworkQueueQOSPolicies(){
		return this.networkQueuePolicies;
	}
	
}
