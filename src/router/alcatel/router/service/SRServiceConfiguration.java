package router.alcatel.router.service;

import java.util.TreeMap;
public class SRServiceConfiguration {
	
	protected TreeMap<Integer, SRVPLSObject> vpls;
	
	public SRServiceConfiguration(){
		this.vpls = new TreeMap<Integer, SRVPLSObject>();
	}
	
	public void addVPLS(SRVPLSObject service){
		this.vpls.put(service.getServiceNumber(), service);
	}
	
	public TreeMap<Integer, SRVPLSObject> getServices(){
		return this.vpls;
	}
	
	public SRVPLSObject getVPLS(Integer serviceNumber){
		return this.vpls.get(serviceNumber);
	}
	
	
}
