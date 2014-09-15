package router.alcatel.router.filter;

import java.util.TreeMap;

public class SRFilterConfiguration {
	
	protected TreeMap<Integer, SRIPFilterObject> ipFilters = null;
	
	public SRFilterConfiguration(){
		this.ipFilters = new TreeMap<Integer, SRIPFilterObject>();
	}
	
	public void addIPFilter(SRIPFilterObject filter){
		this.ipFilters.put(filter.getFilterNumber(), filter);
	}
	
	public TreeMap<Integer, SRIPFilterObject> getIPFilters(){
		return this.ipFilters;
	}
	
	public SRIPFilterObject getIPFilter(Integer filternumber){
		return this.ipFilters.get(filternumber);
	}
}
