package router.alcatel.router.ospf;

import router.alcatel.router.*;
import java.util.TreeMap;

public class SROSPFArea extends AlcatelObject {

	protected String areaName = "";
	protected TreeMap<String, SROSPFInterface> interfaces = null;
	public SROSPFArea(String areaname){
		super(AlcatelObjectType.OSPFAREA);
		this.areaName = areaname;
		this.interfaces = new TreeMap<String, SROSPFInterface>();
	}
	
	public String getName(){
		return this.areaName;
	}
	
	public void setName(String areaname){
		this.areaName = areaname;
	}
	
	public void addInterface(SROSPFInterface iface){
		this.interfaces.put(iface.getName(), iface);
	}
	
	public SROSPFInterface getInterface(String ifaceName){
		return this.interfaces.get(ifaceName);
	}
	
	public TreeMap<String, SROSPFInterface> getInterfaces(){
		return this.interfaces;
	}
}
