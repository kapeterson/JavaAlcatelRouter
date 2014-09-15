package router.alcatel.router.service;

import router.alcatel.router.*;

public class SRServiceObject extends AlcatelObject{
	
	protected Integer serviceNumber = 0;
	protected String serviceDescription = "";
	
	public SRServiceObject(){
		super(AlcatelObjectType.SERVICE);
	}
	
	public SRServiceObject(Integer serviceNumber){
		super(AlcatelObjectType.SERVICE);
		this.serviceNumber = serviceNumber;

	}

	public boolean isService(){
		return true;
	}
	
	public void setDescription(String desc){
		this.serviceDescription = desc;
	}
	
	public String getDesription(){
		return this.serviceDescription;
	}
	
	public void setServiceNumber(Integer snum){
		this.serviceNumber = snum;
	}
	
	public Integer getServiceNumber(){
		return this.serviceNumber;
	}
}
