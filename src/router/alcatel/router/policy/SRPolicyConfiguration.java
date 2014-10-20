package router.alcatel.router.policy;

import java.util.TreeMap;

/**
 * Model the router policy conifguration section
 * @author Kris Peterson
 *
 */
public class SRPolicyConfiguration {
	
	/** Map of all policy communities
	 *  key = policy name
	 *  value = policy object
	 */
	protected TreeMap<String, SRPolicyCommunity> communities;
	
	/** Map of all policy statements **/
	protected TreeMap<String, SRPolicyStatement> statements;
	
	public SRPolicyConfiguration(){
		
		this.communities = new TreeMap<String, SRPolicyCommunity>();
		this.statements = new TreeMap<String, SRPolicyStatement>();
	}
	
	
	/** Add the policy community to the policy configuration **/
	public void addCommunity(SRPolicyCommunity community){
		this.communities.put(community.getName(), community);
	}
	
	/**
	 * Get the full map of configured communities
	 * key = community name
	 * value = community object
	 * @return  Map of all configured communities
	 */
	public TreeMap<String, SRPolicyCommunity> getCommunities(){
		return this.communities;
	}
	
	/** Is a community with the provided name configured **/
	public boolean hasCommunity(String communityName){
		return this.communities.containsKey(communityName);
	}
	
	/**
	 * Get the community object with the provided name
	 * @param communityName
	 * @return SRPolicyCommunity 
	 */
	public SRPolicyCommunity getCommunity(String communityName){
		return this.communities.get(communityName);
	}
	
	/**
	 * Adds a policy statement to the policy configuration
	 * @param SRPolicyStatement statement
	 */
	public void addStatement(SRPolicyStatement statement){
		this.statements.put(statement.getName(), statement);
			
	}
	
	/**
	 * Get a list of all the policy statements
	 * key = Policy Statement name
	 * value = SRPolicyStatement object
	 * @return
	 */
	public TreeMap<String, SRPolicyStatement> getStatements(){
		return this.statements;
	}
	
	/** does the policy configuration contain a policy statement with the provided name **/
	public boolean hasStatement(String statementName){
		return this.statements.containsKey(statementName);
	}
	
	
	/**
	 * Get the SRPolicyStatement object statement with the provided name
	 * @param statementName
	 * @return SRPolicyStatement
	 */
	public SRPolicyStatement getStatement(String statementName){
		return this.statements.get(statementName);
	}

}
