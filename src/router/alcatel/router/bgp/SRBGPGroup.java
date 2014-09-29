package router.alcatel.router.bgp;

import java.util.ArrayList;

import router.alcatel.router.*;

public class SRBGPGroup extends AlcatelObject {
	
	protected ArrayList<String> neighbors = new ArrayList<String>();

	
	public SRBGPGroup(String groupName){
		super(AlcatelObjectType.BGPGROUP);
		this.setName(groupName);
	}
	
	public void addNeighbor(String neighborIP){
		this.neighbors.add(neighborIP);
	}
	
	public boolean hasNeighbor(String neighborIP){
		return this.neighbors.contains(neighborIP);
	}
	
	public ArrayList<String> getNeighbors(){
		return this.neighbors;
	}
}
