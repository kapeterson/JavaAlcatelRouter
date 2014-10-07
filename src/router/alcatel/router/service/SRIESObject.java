package router.alcatel.router.service;

import java.util.TreeMap;

public class SRIESObject extends SRServiceObject {
	
	protected TreeMap<String, SRServiceInterface> interfaces = new TreeMap<String, SRServiceInterface>();
	
	public SRIESObject(Integer iesnumber){
		super(iesnumber);
		this.setName(String.valueOf(iesnumber));
	}
	
	public boolean isIESObject(){
		return true;
	}
	
	public void addInterface(SRServiceInterface iface){
		this.interfaces.put(iface.getName(), iface);
	}
	
	public boolean hasInterface(String interfaceName){
		return this.interfaces.containsKey(interfaceName);
	}
	
	public TreeMap<String, SRServiceInterface> getInterfaces(){
		return this.interfaces;
	}
	
	public SRServiceInterface getInterface(String interfaceName){
		return this.interfaces.get(interfaceName);
	}
	
}
