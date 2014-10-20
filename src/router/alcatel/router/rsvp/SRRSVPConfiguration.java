package router.alcatel.router.rsvp;

import java.util.TreeMap;

/**
 * Models the RSVP Configuration section of the 7x50
 * @author Kris Peterson
 *
 */
public class SRRSVPConfiguration {
	protected TreeMap<String, SRRSVPInterface> interfaces;

	public SRRSVPConfiguration(){
		this.interfaces = new TreeMap<String, SRRSVPInterface>();
	}
	
	/** Add the RSVP Interface to the RSVP configuration **/
	public void addInterface(SRRSVPInterface rsvpInterfaceObject){
		this.interfaces.put(rsvpInterfaceObject.getName(), rsvpInterfaceObject);
	}
	
	
	/** Get the RSVP interfae with the name provided **/
	public SRRSVPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	
	/** GEt a map of all RSVP interfaces **/
	public TreeMap<String, SRRSVPInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
