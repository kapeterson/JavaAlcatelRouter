package router.alcatel.router.bgp;

import java.util.ArrayList;

import router.alcatel.router.*;

public class SRBGPGroup extends AlcatelObject {
	
	/** List of the ip address represented as a string of all neighbors **/
	protected ArrayList<String> neighbors = new ArrayList<String>();

	
	/**
	 * Create a BGP group with the provided groupName
	 * @param groupName 
	 */
	public SRBGPGroup(String groupName){
		super(AlcatelObjectType.BGPGROUP);
		this.setName(groupName);
	}
	
	
	/**
	 * Adds a neighbor with the ip address provided in string format
	 * @param neighborIP
	 */
	public void addNeighbor(String neighborIP){
		this.neighbors.add(neighborIP);
	}
	
	
	/**
	 * Does the bgp group have the neighbor configured
	 * @param neighborIP
	 * @return
	 */
	public boolean hasNeighbor(String neighborIP){
		return this.neighbors.contains(neighborIP);
	}
	
	/**
	 * Get an ArrayList containing all the neighbors int he group
	 * @return ArrayList of string containing the ip of all neighbors
	 */
	public ArrayList<String> getNeighbors(){
		return this.neighbors;
	}
}
