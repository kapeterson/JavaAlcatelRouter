package router.alcatel.router.qos;

import java.util.TreeMap;

public class SRQOSConfiguration {
	
	protected TreeMap<Integer, NetworkQOSPolicy> networkPolicies = null;
	protected TreeMap<Integer, SAPIngressQOSPolicy> sapIngressPolicies = null;
	protected TreeMap<Integer, SAPEgressQOSPolicy> sapEgressPolicies = null;
	protected TreeMap<String, NetworkQueueQOSPolicy> networkQueuePolicies = null;

	
	public SRQOSConfiguration(){
		networkPolicies = new TreeMap<Integer, NetworkQOSPolicy>();
		sapIngressPolicies = new TreeMap<Integer, SAPIngressQOSPolicy>();
		sapEgressPolicies = new TreeMap<Integer, SAPEgressQOSPolicy>();
		networkQueuePolicies = new TreeMap<String, NetworkQueueQOSPolicy>();
	}
	
	public void addNetworkQOSPolicy(NetworkQOSPolicy policy){
		this.networkPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public void addNetworkQueueQOSPolicy(NetworkQueueQOSPolicy policy){
		this.networkQueuePolicies.put(policy.getPolicyName(), policy);
	}
	
	public void addSAPIngressQOSPolicy(SAPIngressQOSPolicy policy){
		this.sapIngressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public void addSAPEgressQOSPolicy(SAPEgressQOSPolicy policy){
		this.sapEgressPolicies.put(policy.getPolicyNumber(), policy);
	}
	
	public NetworkQOSPolicy getNetworkQOSPolicy(Integer policynumber){
		return this.networkPolicies.get(policynumber);
	}
	
	public NetworkQueueQOSPolicy getNetworkQueueQOSPolicy(String policyName){
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
	
	public TreeMap<Integer, NetworkQOSPolicy> getNetworkQOSPolicies(){
		return this.networkPolicies;
	}
	
	public TreeMap<String, NetworkQueueQOSPolicy> getNetworkQueueQOSPolicies(){
		return this.networkQueuePolicies;
	}
	
}
