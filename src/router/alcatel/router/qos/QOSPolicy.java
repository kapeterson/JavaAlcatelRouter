package router.alcatel.router.qos;

import router.alcatel.router.*;

public abstract class QOSPolicy extends AlcatelObject{

	protected String description = "";
	public QOSPolicy(){
		super(AlcatelObjectType.QOSPOLICY);
		description = "";
	}
	
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public String getDescription(){
		return this.getDescription();
	}
	
	public boolean isQOSPolicy(){
		return true;
	}
	
}
