package router.alcatel.router.filter;

import java.util.TreeMap;

public class SRFilterConfiguration {
	
	protected TreeMap<Integer, SRIPFilterObject> ipFilters = null;
	protected TreeMap<Integer, SRMacFilterObject> macFilters = null;

	public SRFilterConfiguration(){
		this.ipFilters = new TreeMap<Integer, SRIPFilterObject>();
		this.macFilters = new TreeMap<Integer, SRMacFilterObject>();
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
	
	
	public void addMacFilter(SRMacFilterObject filter){
		this.macFilters.put(filter.getFilterNumber(), filter);
	}
	
	public TreeMap<Integer, SRMacFilterObject> getMacFilters(){
		return this.macFilters;
	}
	
	public SRMacFilterObject getMacFilter(Integer filternumber){
		return this.macFilters.get(filternumber);
	}
}
