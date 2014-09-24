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
	
	public void addPort(SRPortObject port){
		this.ports.put(port.getName(), port);
	}
	
	public SRPortObject getPort(String portName){
		return this.ports.get(portName);
	}
	
	public TreeMap<String, SRPortObject> getPorts(){
		return this.ports;
	}
	
	public boolean hasPort(String portName){
		return this.ports.containsKey(portName);
	}
}
