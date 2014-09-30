package router.alcatel.router.filter;

import java.util.TreeMap;

/**
 * Models the Filter configuration section on the 7x50
 * @author Kris Peterson
 *
 */
public class SRFilterConfiguration {
	
	/** List of the configured IP Filters **/
	protected TreeMap<Integer, SRIPFilterObject> ipFilters = null;
	
	/** List of the configure mac Filters **/
	protected TreeMap<Integer, SRMacFilterObject> macFilters = null;

	public SRFilterConfiguration(){
		this.ipFilters = new TreeMap<Integer, SRIPFilterObject>();
		this.macFilters = new TreeMap<Integer, SRMacFilterObject>();
	}
	
	/**
	 * Add the IP Filter to the configuration
	 * @param filter
	 */
	public void addIPFilter(SRIPFilterObject filter){
		this.ipFilters.put(filter.getFilterNumber(), filter);
	}
	
	
	/**
	 * Return a TreeMap of all the configured IP Filters.
	 * value = Number of the ip filter
	 * key = filterObject
	 * @return
	 */
	public TreeMap<Integer, SRIPFilterObject> getIPFilters(){
		return this.ipFilters;
	}
	
	
	/**
	 * Returns the IP Filter Object with the provided number
	 * @param filternumber
	 * @return
	 */
	public SRIPFilterObject getIPFilter(Integer filternumber){
		return this.ipFilters.get(filternumber);
	}
	
	
	/**
	 * Adds the mac filter object to the configuration
	 * @param filter
	 */
	public void addMacFilter(SRMacFilterObject filter){
		this.macFilters.put(filter.getFilterNumber(), filter);
	}
	
	/**
	 * Return a list of all configured mac filters as a TreeMap
	 * Filter number = key
	 * Mac filte robject = value
	 * @return
	 */
	public TreeMap<Integer, SRMacFilterObject> getMacFilters(){
		return this.macFilters;
	}
	
	/**
	 * Returns the mac filter object with the provided number 
	 * @param filternumber
	 * @return
	 */
	public SRMacFilterObject getMacFilter(Integer filternumber){
		return this.macFilters.get(filternumber);
	}
}
