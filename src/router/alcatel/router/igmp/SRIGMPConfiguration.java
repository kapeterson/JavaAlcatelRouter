package router.alcatel.router.igmp;
import java.util.TreeMap;

import router.alcatel.router.pim.SRPIMInterface;

public class SRIGMPConfiguration {
	
	protected TreeMap<String, SRIGMPInterface> interfaces;
	
	public SRIGMPConfiguration(){
		this.interfaces = new TreeMap<String, SRIGMPInterface>();
	}
	
	public void addInterface(SRIGMPInterface igmpInterface){
		this.interfaces.put(igmpInterface.getName(), igmpInterface);
	}
	
	public SRIGMPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SRIGMPInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
