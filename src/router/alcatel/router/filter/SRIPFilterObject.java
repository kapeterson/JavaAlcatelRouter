package router.alcatel.router.filter;

public class SRIPFilterObject extends SRFilterObject {
	
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
