package router.alcatel.router.ospf;

import router.alcatel.router.*;
import java.util.TreeMap;

/**
 * Models an OSPF Area
 * @author Kris Peterson
 *
 */
public class SROSPFArea extends AlcatelObject {

	/** name of the ospf area **/
	protected String areaName = "";
	
	/** OSPF interfaces configured in the ospf area **/
	protected TreeMap<String, SROSPFInterface> interfaces = null;
	
	public SROSPFArea(String areaname){
		super(AlcatelObjectType.OSPFAREA);
		this.areaName = areaname;
		this.interfaces = new TreeMap<String, SROSPFInterface>();
	}
	
	/** Get the area name **/
	public String getAreaName(){
		return this.areaName;
	}
	
	/** Set the area name **/
	public void setAreaName(String areaname){
		this.areaName = areaname;
	}
	
	/** add the OSPF interface to the ospf area **/
	public void addInterface(SROSPFInterface iface){
		this.interfaces.put(iface.getName(), iface);
	}
	
	/** Get the ospf interface with the provided name **/
	public SROSPFInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	/** get a map of all configured OSPF interfaces.  Interface name is the key
	 * OSPF interface is the value object
	 * @return TreeMap of InterfaceName => OSPFInterface
	 */
	public TreeMap<String, SROSPFInterface> getInterfaces(){
		return this.interfaces;
	}
}
