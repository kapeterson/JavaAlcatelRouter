package router.alcatel.router.ospf;

import java.util.TreeMap;

/**
 * MOdel the OSPF configuration
 * @author Kris Peterson
 *
 */
public class SROSPFConfiguration {

	/**
	 * Map of configured OSPF areas
	 * Area Name = Key
	 * Area Object = Value
	 */
	protected TreeMap<String, SROSPFArea> areas;
	
	public SROSPFConfiguration(){
		this.areas = new TreeMap<String, SROSPFArea>();
	}
	
	/** Add the area to the ospf configuration **/
	public void addArea(SROSPFArea area){
		this.areas.put(area.getAreaName(), area);
	}
	
	/** get the area objecdt with the provided name **/
	public SROSPFArea getArea(String areaname){
		return this.areas.get(areaname);
	}
	
	/** Get a Map of configured ospf areas **/
	public TreeMap<String, SROSPFArea> getAreas(){
		return this.areas;
	}
	
	public boolean hasArea(String areaName){
		return this.areas.containsKey(areaName);
	}
}
