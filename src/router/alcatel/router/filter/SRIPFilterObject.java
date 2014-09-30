package router.alcatel.router.filter;

import java.util.TreeMap;


public class SRIPFilterObject extends SRFilterObject {
	
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
	
	public void addEntry(SRIPFilterEntry entry){
		this.entries.put(Integer.parseInt(entry.getName()), entry);
	}
	
	public TreeMap<Integer, SRIPFilterEntry> getEntries(){
		return this.entries;
	}
	
	public SRIPFilterEntry getEntry(Integer entryNumber){
		return this.entries.get(entryNumber);
	}
}
