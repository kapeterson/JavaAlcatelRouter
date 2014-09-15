package router.alcatel.router.filter;

public abstract class SRFilterEntryObject {
	
	public Integer entryNumber = 0;
	public String entryDescription = "";
	public FilterAction action = FilterAction.Drop;
	
	public SRFilterEntryObject(){
		this.entryNumber = 0;
		this.entryDescription = "";
		this.action = FilterAction.Drop;
	}
	
	public SRFilterEntryObject(Integer entryNumber){
		this.entryNumber = entryNumber;
		this.entryDescription = "";
		this.action = FilterAction.Drop;

		
	}
	
	public void setDescription(String desc){
		this.entryDescription = desc;
	}
	
	public void setNumber(Integer num){
		this.entryNumber = num;
	}
	
	public boolean isFilterEntryObject(){
		return true;
	}
	
	public void setAction(FilterAction action){
		this.action = action;
	}
	
	public FilterAction getAction(){
		return this.action;
	}
	
}
