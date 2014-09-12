package router.alcatel.router;

// object types
// HARDWARE


/**
 * Base AlcatelObject class from which all objects of the router should derive.
 * Allows us to determine the type of object we are dealing with.
 * @author pete
 *
 */
public class AlcatelObject {

	
	private String objectName;
	private AlcatelObjectType objectType;
	
	public AlcatelObject(){
		objectName = "";
		objectType = AlcatelObjectType.NONE;
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
		return false;
	}
	
	public boolean isIOMObject(){
		return false;
	}
	
	public boolean isMDAObject(){
		return false;
	}
	
	public boolean isCPMObject(){
		return false;
	}
	
	
}
