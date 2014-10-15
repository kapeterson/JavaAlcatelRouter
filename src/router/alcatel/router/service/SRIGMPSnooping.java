package router.alcatel.router.service;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.igmp.SRIGMPStaticJoin;

public class SRIGMPSnooping extends AlcatelObject {
	
	
	protected boolean isMrouter = false;
	protected TreeMap<String, String> staticJoins = new TreeMap<String, String>();
	
	
	public SRIGMPSnooping(){
		super(AlcatelObjectType.IGMPSNOOPING);

	}
	
	public void setMRouter(boolean value){
		this.isMrouter = value;
	}
	
	public boolean isMrouter(){
		return this.isMrouter;
	}
	
	public void addStaticJoin(String groupAddress, String source){
		this.staticJoins.put(groupAddress, source);

	}
	
	public void addStaticJoin(SRIGMPStaticJoin join){
		this.staticJoins.put(join.getGroup(), join.getSource());
	}
	
	public TreeMap<String, String> getStaticJoins(){
		return this.staticJoins;
	}
	
	public String getSource(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}

}
