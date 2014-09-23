package router.alcatel.router.rsvp;

import java.util.TreeMap;

public class SRRSVPConfiguration {
	protected TreeMap<String, SRRSVPInterface> interfaces;

	public SRRSVPConfiguration(){
		this.interfaces = new TreeMap<String, SRRSVPInterface>();
	}
	
	public void addInterface(SRRSVPInterface rsvpInterfaceObject){
		this.interfaces.put(rsvpInterfaceObject.getName(), rsvpInterfaceObject);
	}
	
	public SRRSVPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRRSVPInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
