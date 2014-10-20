package router.alcatel.router.qos;

import router.alcatel.router.*;

public abstract class QOSPolicy extends AlcatelObject{

	protected String description = "";
	public QOSPolicy(){
		super(AlcatelObjectType.QOSPOLICY);
		description = "";
	}
	
	
	/** set the description of the QOS policy **/
	public void setDescription(String desc){
		this.description = desc;
	}
	
	/** get the description of the QOS Policy **/
	public String getDescription(){
		return this.getDescription();
	}
	
	public boolean isQOSPolicy(){
		return true;
	}
	
}
