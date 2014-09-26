package router.alcatel.router.impm;

import java.util.TreeMap;


/**
 * Models IMPM Configuration section
 * @author Kris Peterson
 *
 */
public class SRIMPMConfiguration {

	/** Hash for bandwidth policies **/
	protected TreeMap<String, SRBandwidthPolicy> policies = new TreeMap<String, SRBandwidthPolicy>();
	
	public SRIMPMConfiguration(){
		
	}
	
	
	/**
	 * Add a bandwidht policy to the configuration
	 * @param policy object instantiation of a bandwidth policy
	 */
	public void addBandwidthPolicy(SRBandwidthPolicy policy){
		this.policies.put(policy.getName(), policy);
	}
	
	
	/**
	 * Does the IMPM Configuration have the policy
	 * @param policyName
	 * @return whether or not the policy exists
	 */
	public boolean hasBandwidthPolicy(String policyName){
		return this.policies.containsKey(policyName);
	}
	
	
	/**
	 * Get the entire map of name to bandwidth policy
	 * @return TreeMap with String as the key and the bandwidth policy as the value
	 */
	public TreeMap<String, SRBandwidthPolicy> getPolicies(){
		return this.policies;
	}
	
	
	/**
	 * Returns teh bandwidth policy for the given name
	 * @param policyname
	 * @return SRBandwidthPolicy Object 
	 */
	public SRBandwidthPolicy getPolicy(String policyname){
		return this.policies.get(policyname);
	}
	
}
