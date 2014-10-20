package router.alcatel.router.igmp;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models an IGMP Interface 
 * @author Kris Peterson
 *
 */
public class SRIGMPInterface extends AlcatelObject{
	
	private TreeMap<String, SRIGMPStaticJoin> staticJoins = new TreeMap<String, SRIGMPStaticJoin>();
	
	/**
	 * Create an igmp interface with the provided name
	 * @param interfacename
	 */
	public SRIGMPInterface(String interfacename){
		super(AlcatelObjectType.IGMPINTERFACE);
		this.setName(interfacename);
	}
	
	
	/** Adds a static join to the interface **/
	public void addStaticJoin(SRIGMPStaticJoin staticJoin){
		this.staticJoins.put(staticJoin.getGroup(), staticJoin);
	}
	
	
	/** Adds a static join to the interface **/
	public void addStaticJoin(String groupAddress, SRIGMPStaticJoin join){
		this.staticJoins.put(groupAddress, join);
	}
	
	
	/** Gets a TreeMap list of the static joins under the interface **/
	public TreeMap<String, SRIGMPStaticJoin> getStaticJoins(){
		return this.staticJoins;
	}
	
	/** Returns the soure address conigured for the provided group **/
	public SRIGMPStaticJoin getGroupSource(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
	
	/** Does the interface have a static join for the provided group address**/
	public boolean hasStaticJoin(String groupAddress){
		return this.staticJoins.containsKey(groupAddress);
	}
	
	
	/** get the static join object for the provided group address **/
	public SRIGMPStaticJoin getStaticJoin(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
	/** Get the number of static joins configured on the interface **/
	public int getStaticJoinCount(){
		return this.staticJoins.size();
	}
	
}
