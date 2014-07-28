
// object types
// HARDWARE

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
	
	
}
