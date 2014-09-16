package router.alcatel.router.pim;

import java.util.TreeMap;

public class SRPIMConfiguration {
	protected TreeMap<String, SRPIMInterface> interfaces;
	
	public SRPIMConfiguration(){
		this.interfaces = new TreeMap<String, SRPIMInterface>();
	}
	
	public void addInterface(SRPIMInterface interfaceObject){
		this.interfaces.put(interfaceObject.getName(), interfaceObject);
	}
	
	public SRPIMInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRPIMInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
}
