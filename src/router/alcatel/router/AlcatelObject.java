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
	
	protected void setObjectName(String objName){
		objectName = objName;
	}
	
	protected void setObjectType(AlcatelObjectType objecttype){
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
	

	
}
