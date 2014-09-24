package router.alcatel.router.policy;

import java.util.TreeMap;

public class SRPolicyConfiguration {
	
	protected TreeMap<String, SRPolicyCommunity> communities;
	protected TreeMap<String, SRPolicyStatement> statements;
	public SRPolicyConfiguration(){
		
		this.communities = new TreeMap<String, SRPolicyCommunity>();
		this.statements = new TreeMap<String, SRPolicyStatement>();
	}
	
	public void addCommunity(SRPolicyCommunity community){
		this.communities.put(community.getName(), community);
	}
	
	public TreeMap<String, SRPolicyCommunity> getCommunities(){
		return this.communities;
	}
	
	public boolean hasCommunity(String communityName){
		return this.communities.containsKey(communityName);
	}
	
	public SRPolicyCommunity getCommunity(String communityName){
		return this.communities.get(communityName);
	}
	
	public void addStatement(SRPolicyStatement statement){
		this.statements.put(statement.getName(), statement);
			
	}
	
	public TreeMap<String, SRPolicyStatement> getStatements(){
		return this.statements;
	}
	
	public boolean hasStatement(String statementName){
		return this.statements.containsKey(statementName);
	}
	
	public SRPolicyStatement getStatement(String statementName){
		return this.statements.get(statementName);
	}

}
