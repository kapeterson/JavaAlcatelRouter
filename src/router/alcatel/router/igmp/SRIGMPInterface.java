package router.alcatel.router.igmp;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

/**
 * Models an IGMP Interface Configuration
 * @author Kris Peterson
 *
 */
public class SRIGMPInterface extends AlcatelObject{
	
	private TreeMap<String, SRIGMPStaticJoin> staticJoins = new TreeMap<String, SRIGMPStaticJoin>();
	
	public SRIGMPInterface(String interfacename){
		super(AlcatelObjectType.IGMPINTERFACE);
		this.setName(interfacename);
	}
	
	public void addStaticJoin(SRIGMPStaticJoin staticJoin){
		this.staticJoins.put(staticJoin.getGroup(), staticJoin);
	}
	
	public void addStaticJoin(String groupAddress, SRIGMPStaticJoin join){
		this.staticJoins.put(groupAddress, join);
	}
	
	public TreeMap<String, SRIGMPStaticJoin> getStaticJoins(){
		return this.staticJoins;
	}
	
	public SRIGMPStaticJoin getGroupSource(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
	public boolean hasStaticJoin(String groupAddress){
		return this.staticJoins.containsKey(groupAddress);
	}
	
	
	public SRIGMPStaticJoin getStaticJoin(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
}
