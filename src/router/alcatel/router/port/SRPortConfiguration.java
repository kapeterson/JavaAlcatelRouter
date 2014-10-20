package router.alcatel.router.port;

import java.util.TreeMap;

/**
 * Models the port configuration section of an Alcatel router.
 * @author pete
 *
 */
public class SRPortConfiguration {

	/**
	 * TreeMap / Hashtable containing all port objects
	 */
	protected TreeMap<String, SRPortObject> ports = null;
			
	public SRPortConfiguration(){
		ports = new TreeMap<String, SRPortObject>();
		
	}
	
	/**
	 * Add the port to the port configuration
	 * @param port
	 */
	public synchronized void addPort(SRPortObject port){
		this.ports.put(port.getName(), port);
		
	}
	
	
	/**
	 * Get the port object with the provided name
	 * @param portName
	 * @return SRPortObject
	 */
	public SRPortObject getPort(String portName){
		return this.ports.get(portName);
	}
	
	
	/**
	 * Return a TreeMap of all ports in the port configuration
	 * value = portname
	 * key = SRPortObject
	 * @return TreeMap of all ports in the port configuration
	 */
	public TreeMap<String, SRPortObject> getPorts(){
		return this.ports;
	}
	
	
	/** does the port configuration have a port with the provided name **/
	public boolean hasPort(String portName){
		return this.ports.containsKey(portName);
	}
	

}
