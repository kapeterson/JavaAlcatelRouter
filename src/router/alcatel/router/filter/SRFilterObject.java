package router.alcatel.router.filter;

public abstract class SRFilterObject {
	
	protected Integer filterNumber = 0;
	protected String description = "";
	
	public SRFilterObject(){
		this.filterNumber = 0;
		this.description = "";
	}
	public SRFilterObject(Integer filterNumber){
		this.filterNumber = filterNumber;
		this.description = "";
	}
	
	public void setFilterNumber(Integer number){
		this.filterNumber = number;
	}
	
	public boolean isFilterObject(){
		return true;
	}
}
