package router.alcatel.router.routerinterface;
import java.util.TreeMap;

public class SRInterfaceConfiguration {
	
	protected TreeMap<String, SRRouterInterface> interfaces = null;
	
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
}
