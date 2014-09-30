package router.alcatel.router.bgp;

import java.util.TreeMap;

/**
 * Models BGP configuration section on the 7x50 Router.
 * @author Kris Peterson
 *
 */
public class SRBGPConfiguration {

	/** list of BGP groups configured **/
	protected TreeMap<String, SRBGPGroup> groups = new TreeMap<String, SRBGPGroup>();
	
	/** Constructor **/
	public SRBGPConfiguration(){
		
	}
	
	
	/**
	 * Adds a bgp group to the configuration
	 * @param group SRBGPGroup object to add
	 */
	public void addBGPGroup(SRBGPGroup group){
		this.groups.put(group.getName(), group);
	}
	
	/**
	 * Is a group with the provided name configured?
	 * @param groupName
	 * @return boolean
	 */
	public boolean hasGroup(String groupName){
		return this.groups.containsKey(groupName);
	}
	
	
	/**
	 * Get a TreeMap list of all the bgp groups.
	 * Group name is the key and the SRBGPGroup is the value
	 * @return
	 */
	public TreeMap<String, SRBGPGroup>  getGroups(){
		return this.groups;
		
	}
	
	/**
	 * Get the SRBGP group with the provided name
	 * @param groupName
	 * @return the SRBGPGroup
	 */
	public SRBGPGroup getGroup(String groupName){
		return this.groups.get(groupName);
	}
}
