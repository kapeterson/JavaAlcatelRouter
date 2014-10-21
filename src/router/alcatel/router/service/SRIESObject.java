package router.alcatel.router.service;

import java.util.TreeMap;

/**
 * Models an IES on 7x50
 * @author Kris Peterson
 *
 */
public class SRIESObject extends SRServiceObject {
	
	/** Map of all interfaces configured under the IES **/
	protected TreeMap<String, SRServiceInterface> interfaces = new TreeMap<String, SRServiceInterface>();
	
	public SRIESObject(Integer iesnumber){
		super(iesnumber);
		this.setName(String.valueOf(iesnumber));
	}
	
	
	public boolean isIESObject(){
		return true;
	}
	
	/** Add the interface object to the IES  **/
	public void addInterface(SRServiceInterface iface){
		this.interfaces.put(iface.getName(), iface);
	}
	
	/** Does the IES have an interface by the provided name **/
	public boolean hasInterface(String interfaceName){
		return this.interfaces.containsKey(interfaceName);
	}
	
	
	/**
	 * Get the full map of all Interface names to Interface Objectds
	 * key = interfaceName
	 * Value = SRServiceInterface object
	 * @return Map of all interfaces
	 */
	public TreeMap<String, SRServiceInterface> getInterfaces(){
		return this.interfaces;
	}
	
	
	/** Get the SRServiceInterface with the provided name **/
	public SRServiceInterface getInterface(String interfaceName){
		return this.interfaces.get(interfaceName);
	}
	
}
