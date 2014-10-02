package router.alcatel.router.service;

import router.alcatel.router.*;

public class SRSAPObject extends AlcatelObject implements SRInterfaceBindingObject, SRServiceChild {

	protected String sapName = "";
	protected String description = "";
	protected AlcatelObject parentService = null;
	public SRSAPIngress INGRESS = new SRSAPIngress();
	public SRSAPEgress EGRESS = new SRSAPEgress();
	
	public SRSAPObject(String sapname, AlcatelObject parentService){
		super(AlcatelObjectType.SAPOBJECT);
		this.setName(sapname);
		this.sapName = sapname;
		this.description = "";
		this.parentService = parentService;
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
	
	public AlcatelObject getParentService(){
		return this.parentService;
	}
	
}
