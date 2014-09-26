package router.alcatel.router.routerinterface;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

import router.alcatel.router.ConfigurationEventListener;

public class SRInterfaceConfiguration {
	
	protected TreeMap<String, SRRouterInterface> interfaces = null;
	protected List<ConfigurationEventListener> eventListeners = new ArrayList<ConfigurationEventListener>();
	
	public SRInterfaceConfiguration(){
		this.interfaces = new TreeMap<String, SRRouterInterface>();
	}
	
	public void addInterface(SRRouterInterface iface){
		this.interfaces.put(iface.getName(), iface);
	}
	
	public TreeMap<String, SRRouterInterface> getInterfaces(){
		return this.interfaces;
	}
	
	public SRRouterInterface getInterface(String intName){
		return this.interfaces.get(intName);
	}
	
	public boolean hasInterface(String intName){
		return this.interfaces.containsKey(intName);
	}
	
	public void addConfigEventListener(ConfigurationEventListener listener){
		this.eventListeners.add(listener);
	}
}
