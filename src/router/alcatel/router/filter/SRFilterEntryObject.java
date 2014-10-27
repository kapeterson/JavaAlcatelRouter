package router.alcatel.router.filter;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;


/**
 * Models a filter entry, ip, mac and ipv6 will extend this object
 * @author Kris Peterson
 *
 */
public abstract class SRFilterEntryObject extends AlcatelObject {
	
	/** Number of the entry **/
	public Integer entryNumber = 0;
	
	/** Entry description **/
	public String entryDescription = "";

	/** Action of the filter entry (drop, forward, etc)**/
	public FilterAction action = FilterAction.Drop;
	
	public SRFilterEntryObject(){
		super(AlcatelObjectType.SRIPFILTERENTRY);
		this.entryNumber = 0;
		this.entryDescription = "";
		this.action = FilterAction.Drop;
	}
	
	public SRFilterEntryObject(Integer entryNumber){
		super(AlcatelObjectType.SRIPFILTERENTRY);

		this.entryNumber = entryNumber;
		this.entryDescription = "";
		this.action = FilterAction.Drop;

		
	}
	
	/** Set the description of the filter entry **/
	public void setDescription(String desc){
		this.entryDescription = desc;
	}
	
	/** Set the number of the filter entry **/
	public void setNumber(Integer num){
		this.entryNumber = num;
	}
	
	
	public int getNumber(){
		return this.entryNumber;
	}
	
	public boolean isFilterEntryObject(){
		return true;
	}
	
	/**
	 * Set the action of the filter object
	 * @param action Valid filteraction from the FilterAction enum type
	 */
	public void setAction(FilterAction action){
		this.action = action;
	}
	
	/** get the action of the filter **/
	public FilterAction getAction(){
		return this.action;
	}
	
}
