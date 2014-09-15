package router.alcatel.router.filter;


public abstract class SRFilterObject {
	
	protected Integer filterNumber = 0;
	protected String description = "";
	protected FilterAction defaultAction = FilterAction.Drop;
	
	public SRFilterObject(){
		this.filterNumber = 0;
		this.description = "";
		this.defaultAction = FilterAction.Drop;
	}
	public SRFilterObject(Integer filterNumber){
		this.filterNumber = filterNumber;
		this.description = "";
		this.defaultAction = FilterAction.Drop;
	}
	
	public Integer getFilterNumber(){
		return this.filterNumber;
	}
	public void setFilterNumber(Integer number){
		this.filterNumber = number;
	}
	
	public boolean isFilterObject(){
		return true;
	}
	
	public void setDefaultAction(FilterAction action){
		this.defaultAction = action;
	}
	
	public FilterAction getDefaultAction(){
		return this.defaultAction;
	}
}
