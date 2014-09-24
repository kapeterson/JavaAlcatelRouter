package router.alcatel.router.policy;

import router.alcatel.router.*;

import java.util.TreeMap;

public class SRPolicyCommunity extends AlcatelObject {
	
	protected TreeMap<String, SRPolicyCommunity> communities;
	public SRPolicyCommunity(String communityName){
		super(AlcatelObjectType.POLICYCOMMUNITY);
		this.setName(communityName);
		this.communities = new TreeMap<String, SRPolicyCommunity>();

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
}
