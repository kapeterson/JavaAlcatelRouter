package router.alcatel.router.service;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.igmp.SRIGMPStaticJoin;


/**
 * Models the IGMP Snooping component on a 7x50
 * @author Kris Peterson
 *
 */
public class SRIGMPSnooping extends AlcatelObject {
	
	
	/** is mrouter configured **/
	protected boolean isMrouter = false;
	
	/** Map of all static joins configured **/
	protected TreeMap<String, SRIGMPStaticJoin> staticJoins = new TreeMap<String, SRIGMPStaticJoin>();
	
	
	public SRIGMPSnooping(){
		super(AlcatelObjectType.IGMPSNOOPING);

	}
	
	/** Set igmp snooping to mrouter mode **/
	public void setMRouter(boolean value){
		this.isMrouter = value;
	}
	
	/** Is mrouter conigured **/
	public boolean isMrouter(){
		return this.isMrouter;
	}
	
	/** Add a static join with the group and source **/
	public void addStaticJoin(String groupAddress, String source){
		SRIGMPStaticJoin join = new SRIGMPStaticJoin(groupAddress, source);
		this.staticJoins.put(groupAddress, join);

	}
	
	/** Add a static join with an SRIGMPStaticJoin provided **/
	public void addStaticJoin(SRIGMPStaticJoin join){
		this.staticJoins.put(join.getGroup(), join);
	}
	
	/**
	 * Get the Map of all static joins under the snooping configuration
	 * key = GroupAddress
	 * value = StaticJoin
	 * @return TreeMap of all static joins configured in the snooping component
	 */
	public TreeMap<String, SRIGMPStaticJoin> getStaticJoins(){
		return this.staticJoins;
	}
	
	/**
	 * Get the static join with the provided source address
	 * @param groupAddress
	 * @return SRIGMPSTaticJoin
	 */
	public SRIGMPStaticJoin getStaticJoin(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
	/** Does the snooping component have a static join with the provided group Address **/
	public boolean hasStaticJoin(String groupAddress){
		return this.staticJoins.containsKey(groupAddress);
	}
	
	/** Get the total number of static joins configured in teh snooping component **/
	public int getStaticJoinCount(){
		return this.staticJoins.size();
	}

}
