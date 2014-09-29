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
}
