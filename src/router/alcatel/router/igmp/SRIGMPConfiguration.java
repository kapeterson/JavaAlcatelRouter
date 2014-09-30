package router.alcatel.router.igmp;
import java.util.TreeMap;

import router.alcatel.router.pim.SRPIMInterface;


/**
 * Models IGMP Configuration on 7x50
 * @author Kris Peterson
 *
 */
public class SRIGMPConfiguration {
	
	/** TreeMap of IGMP Interfaces.  Key=name value=SRIGMPInterface **/
	protected TreeMap<String, SRIGMPInterface> interfaces;
	
	public SRIGMPConfiguration(){
		this.interfaces = new TreeMap<String, SRIGMPInterface>();
	}
	
	
	/** Adds the IGMPInterface to the configuration **/
	public void addInterface(SRIGMPInterface igmpInterface){
		
		this.interfaces.put(igmpInterface.getName(), igmpInterface);
	}
	
	
	/** Get the IGMPInterface with the given name **/
	public SRIGMPInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	
	/** Get a TreeMap of all the IGMPInterfaces **/
	public TreeMap<String, SRIGMPInterface> getInterfaces(){
		return this.interfaces;
		
	}
}
