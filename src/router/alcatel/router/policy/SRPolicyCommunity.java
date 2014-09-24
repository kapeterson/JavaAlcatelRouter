package router.alcatel.router.policy;

import router.alcatel.router.*;
import java.util.ArrayList;

public class SRPolicyCommunity extends AlcatelObject {
	
	protected int asNum = 0;
	protected int commValue = 0;
	protected ArrayList<String> communityList = new ArrayList<String>();
	public SRPolicyCommunity(String communityName){
		super(AlcatelObjectType.POLICYCOMMUNITY);
		this.setName(communityName);
		

	}
	
	public void addMember(String communityID){
		this.communityList.add(communityID);
	}

	public boolean hasMember(String communityID){
		return this.communityList.contains(communityID);
	}
	
	public ArrayList<String> getMembers(){
		return this.communityList;
	}
	
	
	
	
	
}
