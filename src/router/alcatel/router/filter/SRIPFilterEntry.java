package router.alcatel.router.filter;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRIPFilterEntry extends AlcatelObject {
	
	
	protected String description = "";
	
	public SRIPFilterEntry(Integer filterNumber){
		super(AlcatelObjectType.SRIPFILTERENTRY);
		this.setName(filterNumber.toString());
		
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return this.description;
	}
	


}
