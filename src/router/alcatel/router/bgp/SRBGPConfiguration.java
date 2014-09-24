package router.alcatel.router.bgp;

import java.util.TreeMap;

public class SRBGPConfiguration {

	protected TreeMap<String, SRBGPGroup> groups = new TreeMap<String, SRBGPGroup>();
	
	public SRBGPConfiguration(){
		
	}
	
	public void addBGPGroup(SRBGPGroup group){
		this.groups.put(group.getName(), group);
	}
	
	public boolean hasGroup(String groupName){
		return this.groups.containsKey(groupName);
	}
	
	public TreeMap<String, SRBGPGroup>  getGroups(){
		return this.groups;
		
	}
	
	public SRBGPGroup getGroup(String groupName){
		return this.groups.get(groupName);
	}
}
