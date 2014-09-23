package router.alcatel.router.mpls;
import java.util.TreeMap;


public class SRMPLSConfiguration {
	protected TreeMap<String, SRMPLSInterface> interfaces;

	public SRMPLSConfiguration(){
		this.interfaces = new TreeMap<String, SRMPLSInterface>();
	}
	
	public void addInterface(SRMPLSInterface mplsInterfaceObject){
		this.interfaces.put(mplsInterfaceObject.getName(), mplsInterfaceObject);
	}
	
	public SRMPLSInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRMPLSInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
