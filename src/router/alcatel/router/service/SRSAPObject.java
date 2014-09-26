package router.alcatel.router.service;

import router.alcatel.router.*;

public class SRSAPObject extends AlcatelObject implements BindingChild {

	protected String sapName = "";
	protected String description = "";
	
	public SRSAPObject(String sapname){
		super(AlcatelObjectType.SAPOBJECT);
		this.setName(sapname);
		this.sapName = sapname;
		this.description = "";
	}
		
	public String getSAPName(){
		return this.sapName;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public boolean isSAPObject(){
			return true;
	}
	
	@Override
	public boolean isBindingChild(){
		return true;
	}
	
}
