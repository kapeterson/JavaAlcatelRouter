package router.alcatel.router.routerinterface;
import java.util.TreeMap;




/**
 * Models the Interface configuration on the 7x50
 * @author Kris Peterson
 *
 */
public class SRInterfaceConfiguration {
	
	
	/** Map of confiured interfaces **/
	protected TreeMap<String, SRRouterInterface> interfaces = null;
	
	
	/** IP to interface Map **/
	protected TreeMap<String, String> ipMap = new TreeMap<String, String>();
	
	//protected List<ConfigurationEventListener> eventListeners = new ArrayList<ConfigurationEventListener>();
	
	public SRInterfaceConfiguration(){
		this.interfaces = new TreeMap<String, SRRouterInterface>();
	}
	
	/**
	 * Adds the SRERouterInterface to the interface coniguration.  It can be a network or service interface.
	 * @param iface
	 */
	public void addInterface(SRRouterInterface iface){
		this.interfaces.put(iface.getName(), iface);
		
		if ( !iface.getIPv4HostAddress().equals("0.0.0.0")){
			ipMap.put(iface.getIPv4HostAddress(), iface.getName());
		}
	}
	
	
	/**
	 * Return a Map of all router interfaces 
	 * key = interface name
	 * value = SRRouterInterface Object
	 * @return Map of interfaces
	 */
	public TreeMap<String, SRRouterInterface> getInterfaces(){
		return this.interfaces;
	}
	
	/**
	 * get the SRRouterInterface object with the provided interface name.  The interface
	 * can be cast to a network or service interface
	 * @param intName
	 * @return SRRouterInterface
	 */
	public SRRouterInterface getInterface(String intName){
		return this.interfaces.get(intName);
	}
	
	
	/** Does the router have an interface with the IP configured **/
	public boolean hasIPv4Configured(String ip){
		
		return this.ipMap.containsKey(ip);
	}
	
	
	/** Get the name of the interface with the ip address **/
	public String getInterfaceNameByIPv4Address(String ip){
		return this.ipMap.get(ip);
	}
	
	
	public SRRouterInterface getInterfaceByIPv4Address(String ip){
		
		if ( this.hasIPv4Configured(ip)) {
			String intName = this.getInterfaceNameByIPv4Address(ip);
			return this.getInterface(intName);
		}
		
		return null;
	}
	
	/** Does the router have a cofnigured interface with the provided name ? **/
	public boolean hasInterface(String intName){
		return this.interfaces.containsKey(intName);
	}
	
	/*
	public void addConfigEventListener(ConfigurationEventListener listener){
		this.eventListeners.add(listener);
	}
	*/
}
