package router.alcatel.router.service;

import java.util.TreeMap;
public class SRServiceConfiguration {
	
	protected TreeMap<Integer, SRServiceObject> services;
	
	public SRServiceConfiguration(){
		this.services = new TreeMap<Integer, SRServiceObject>();
	}
	
	public void addService(SRServiceObject service){
		this.services.put(service.getServiceNumber(), service);
	}
	
	public TreeMap<Integer, SRServiceObject> getServices(){
		return this.services;
	}
	
	public SRServiceObject getService(Integer serviceNumber){
		return this.services.get(serviceNumber);
	}
	
	
}
