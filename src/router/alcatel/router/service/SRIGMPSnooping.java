package router.alcatel.router.service;

import java.util.TreeMap;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.igmp.SRIGMPStaticJoin;

public class SRIGMPSnooping extends AlcatelObject {
	
	
	protected boolean isMrouter = false;
	protected TreeMap<String, SRIGMPStaticJoin> staticJoins = new TreeMap<String, SRIGMPStaticJoin>();
	
	
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
		SRIGMPStaticJoin join = new SRIGMPStaticJoin(groupAddress, source);
		this.staticJoins.put(groupAddress, join);

	}
	
	public void addStaticJoin(SRIGMPStaticJoin join){
		this.staticJoins.put(join.getGroup(), join);
	}
	
	public TreeMap<String, SRIGMPStaticJoin> getStaticJoins(){
		return this.staticJoins;
	}
	
	public SRIGMPStaticJoin getStaticJoin(String groupAddress){
		return this.staticJoins.get(groupAddress);
	}
	
	public boolean hasStaticJoin(String groupAddress){
		return this.staticJoins.containsKey(groupAddress);
	}
	
	public int getStaticJoinCount(){
		return this.staticJoins.size();
	}

}
