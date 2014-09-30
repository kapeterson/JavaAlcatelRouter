package router.alcatel.router.ldp;

import java.util.TreeMap;


public class SRLDPConfiguration {
	protected TreeMap<String, SRLDPInterface> interfaces;

	public SRLDPConfiguration(){
		this.interfaces = new TreeMap<String, SRLDPInterface>();
	}
	
	public void addInterface(SRLDPInterface ldpInterfaceObject){
		//System.out.println("OK");
		this.interfaces.put(ldpInterfaceObject.getName(), ldpInterfaceObject);
	}
	
	public SRLDPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRLDPInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
