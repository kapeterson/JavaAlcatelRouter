package router.alcatel.router.ldp;

import java.util.TreeMap;

/**
 * Model of the LDP configuration section on the 7x50
 * @author Kris Peterson
 *
 */
public class SRLDPConfiguration {
	protected TreeMap<String, SRLDPInterface> interfaces;

	public SRLDPConfiguration(){
		this.interfaces = new TreeMap<String, SRLDPInterface>();
	}
	
	
	/** Add an ldp interface object to the ldp configuration **/
	public void addInterface(SRLDPInterface ldpInterfaceObject){
		//System.out.println("OK");
		this.interfaces.put(ldpInterfaceObject.getName(), ldpInterfaceObject);
	}
	
	/** Get the LDP interface with the given name **/
	public SRLDPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	
	/** Return a TreeMap/Hash of all the LDP interfaces **/
	public TreeMap<String, SRLDPInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
	public boolean hasInterface(String intName){
		return this.interfaces.containsKey(intName);
	}
}
