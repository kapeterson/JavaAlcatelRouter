package router.alcatel.router.ospf;

import java.util.TreeMap;

public class SROSPFConfiguration {

	protected TreeMap<String, SROSPFArea> areas;
	
	public SROSPFConfiguration(){
		this.areas = new TreeMap<String, SROSPFArea>();
	}
	
	public void addArea(SROSPFArea area){
		this.areas.put(area.getName(), area);
	}
	
	public SROSPFArea getArea(String areaname){
		return this.areas.get(areaname);
	}
	
	public TreeMap<String, SROSPFArea> getAreas(){
		return this.areas;
	}
}
