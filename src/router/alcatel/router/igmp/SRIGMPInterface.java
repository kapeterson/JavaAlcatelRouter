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
	
	private TreeMap<String, String> staticJoins = new TreeMap<String, String>();
	
	public SRIGMPInterface(String interfacename){
		super(AlcatelObjectType.IGMPINTERFACE);
		this.setName(interfacename);
	}
	
	public void addStaticJoin(SRIGMPStaticJoin staticJoin){
		this.staticJoins.put(staticJoin.getGroup(), staticJoin.getSource());
	}
	
	public void addStaticJoin(String groupAddress, String source){
		this.staticJoins.put(groupAddress, source);
	}
	
	public TreeMap<String, String> getStaticJoins(){
		return this.staticJoins;
	}
	
	public boolean hasStaticJoin(String groupAddress){
		return this.staticJoins.containsKey(groupAddress);
	}
	
	
}
