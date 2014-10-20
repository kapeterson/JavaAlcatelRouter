package router.alcatel.router.filter;

import java.util.TreeMap;

/**
 * Models an IP Filter, IPv4 and IPv6 Filters will extend this object
 * @author kp109p
 *
 */
public class SRIPFilterObject extends SRFilterObject {
	
	/** Stores a map of all the filter entires **/
	protected TreeMap<Integer, SRIPFilterEntry> entries = new TreeMap<Integer, SRIPFilterEntry>();
	
	public SRIPFilterObject(){
		super();
			
	}
	
	public SRIPFilterObject(Integer filterNumber){
		super(filterNumber);
	}
	
	public boolean isIPFilterObject(){
		return true;
	}
	
	/**
	 * Add an entry to the entry list of the filter
	 * @param entry
	 */
	public void addEntry(SRIPFilterEntry entry){
		this.entries.put(Integer.parseInt(entry.getName()), entry);
	}
	
	
	/**
	 * Returns a treemap of all entry numbers to filter entries 
	 * @return
	 */
	public TreeMap<Integer, SRIPFilterEntry> getEntries(){
		return this.entries;
	}
	
	
	/**
	 * REturns the IP filter entry with the provided entry number
	 * @param entryNumber
	 * @return
	 */
	public SRIPFilterEntry getEntry(Integer entryNumber){
		return this.entries.get(entryNumber);
	}
}
