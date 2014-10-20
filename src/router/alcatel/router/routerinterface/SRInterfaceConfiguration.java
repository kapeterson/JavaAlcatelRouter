package router.alcatel.router.routerinterface;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import router.alcatel.router.ConfigurationEventListener;


/**
 * Models the Interface configuration on the 7x50
 * @author Kris Peterson
 *
 */
public class SRInterfaceConfiguration {
	
	
	/** Map of confiured interfaces **/
	protected TreeMap<String, SRRouterInterface> interfaces = null;
	
	
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
