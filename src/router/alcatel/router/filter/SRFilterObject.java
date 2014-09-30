package router.alcatel.router.filter;

/**
 * Models a filter on the 7x50.  IP, Mac and IPv6  Filters will extend this class.
 * @author kp109p
 *
 */
public abstract class SRFilterObject {
	
	/** Number of the filter **/
	protected Integer filterNumber = 0;
	
	/** Description of the filter **/
	protected String description = "";
	
	/** Default action of the filter **/
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
	
	/** get the filter number **/
	public Integer getFilterNumber(){
		return this.filterNumber;
	}
	
	/** set the number of the filter **/
	public void setFilterNumber(Integer number){
		this.filterNumber = number;
	}
	
	
	public boolean isFilterObject(){
		return true;
	}
	
	/**
	 * Set the default action of the filter
	 * @param action from the FilterAction enum
	 */
	public void setDefaultAction(FilterAction action){
		this.defaultAction = action;
	}
	
	
	/**
	 * Get the default filter action
	 * @return
	 */
	public FilterAction getDefaultAction(){
		return this.defaultAction;
	}
	
	
	/** Set the description of the filter **/
	public void setDescription(String desc){
		this.description = desc;
	}
	
	/** Get the description of the filter **/
	public String getDescription(){
		return this.description;
	}
}
