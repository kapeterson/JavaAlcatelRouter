package router.alcatel.router.service;

import java.util.TreeMap;
public class SRServiceConfiguration {
	
	protected TreeMap<Integer, SRVPLSObject> vpls;
	protected TreeMap<Integer, SRIESObject> ies;
	protected TreeMap<Integer, SRSDPObject> sdp;

	public SRServiceConfiguration(){
		this.vpls = new TreeMap<Integer, SRVPLSObject>();
		this.ies = new TreeMap<Integer, SRIESObject>();
		this.sdp = new TreeMap<Integer, SRSDPObject>();

	}
	
	public void addVPLS(SRVPLSObject service){
		this.vpls.put(service.getServiceNumber(), service);
	}
	
	public TreeMap<Integer, SRVPLSObject> getVPLSs(){
		return this.vpls;
	}
	
	public SRVPLSObject getVPLS(Integer serviceNumber){
		return this.vpls.get(serviceNumber);
	}
	
	
	public void addIES(SRIESObject ies){
		this.ies.put(ies.getServiceNumber(), ies);
	}
	
	public TreeMap<Integer, SRIESObject> getIESs(){
		return this.ies;
	}
	
	public SRIESObject getIES(Integer iesNumber){
		return this.ies.get(iesNumber);
	}
	
	
	public void addSDP(SRSDPObject sdp){
		this.sdp.put(sdp.getServiceNumber(), sdp);
	}
	
	public SRSDPObject getSDP(Integer snum){
		return this.sdp.get(snum);
	}
	
	public TreeMap<Integer, SRSDPObject> getSDPs(){
		return this.sdp;
	}
	
}
