package router.alcatel.router;

// object types
// HARDWARE


/**
 * Base AlcatelObject class from which all objects of the router should derive.
 * Allows us to determine the type of object we are dealing with.
 * @author pete
 *
 */
public abstract class AlcatelObject {

	
	private String objectName;
	private AlcatelObjectType objectType;
	
	public AlcatelObject(AlcatelObjectType objectType){
		objectName = "";
		this.objectType = objectType;
	}
	
	
	public String getName(){
		return this.objectName;
	}
	
	public void setObjectName(String objName){
		objectName = objName;
	}
	
	public void setObjectType(AlcatelObjectType objecttype){
		objectType = objecttype;
	}
	
	public AlcatelObjectType getObjectType(){
		return objectType;
	}
	
	public boolean isHardwareObject(){
		return false;
	}
	
	public boolean isChassisObject(){
		//return false;
		return (this.getObjectType() == AlcatelObjectType.CHASSIS);
	}
	
	public boolean isIOMObject(){
		//return false;
		return ( this.getObjectType() == AlcatelObjectType.IOM);
	}
	
	public boolean isMDAObject(){
		
		//return false;
		return ( this.getObjectType() == AlcatelObjectType.MDA);
	}
	
	public boolean isCPMObject(){
		//return false;
		return (this.getObjectType() == AlcatelObjectType.CPM);
	}
	
	public boolean isQOSPolicy(){
		return false;
	}
	
	public boolean isNetworkQOSPolicy(){
		return ( this.getObjectType() == AlcatelObjectType.NETWORKQOSPOLICY);
	}
	
	public boolean isNetworkQueueQOSPolicy(){
		return ( this.getObjectType() == AlcatelObjectType.NETWORKQUEUEQOSPOLICY);
	}
	
	public boolean isSAPEgressQOSPolicy(){
		return ( this.getObjectType() == AlcatelObjectType.SAPEGRESSQOSPOLICY);
	}
	
	public boolean isSAPIngressQOSPolicy(){
		return ( this.getObjectType() == AlcatelObjectType.SAPINGRESSQOSPOLICY);
	}
	
	public boolean isSAPQOSPolicy(){
		return false;
	}
	

	
}
