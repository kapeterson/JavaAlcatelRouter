package router.alcatel.router.pim;

import java.util.TreeMap;

/**
 * Models PIM Configuration section in the 7x50
 * @author kp109p
 *
 */
public class SRPIMConfiguration {
	
	/**
	 * Map of PIM Interfaces
	 * key = interface name
	 * value = PIM Interface object
	 */
	protected TreeMap<String, SRPIMInterface> interfaces;
	
	public SRPIMConfiguration(){
		this.interfaces = new TreeMap<String, SRPIMInterface>();
	}
	
	/**
	 * Add the pim interface to the configuration
	 * @param interfaceObject
	 */
	public void addInterface(SRPIMInterface interfaceObject){
		this.interfaces.put(interfaceObject.getName(), interfaceObject);
	}
	
	
	/** GEt the PIM Interface with the provided name **/
	public SRPIMInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	
	/**
	 * Return the map of all PIM Interfaces
	 * @return Map of PIM Interface Name => Interface Object
	 */
	public TreeMap<String, SRPIMInterface> getInterfaces(){
		return this.interfaces;
		
	}
	
}
