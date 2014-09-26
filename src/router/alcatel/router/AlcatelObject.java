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
	protected AlcatelObject parent = null;
	
	public AlcatelObject(AlcatelObjectType objectType){
		objectName = "";
		this.objectType = objectType;
	}
	
	public void setName(String oName){
		this.objectName = oName;
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
	
	
	public boolean isFilterObject(){
		return false;
	}
	
	public boolean isFilterEntryObject(){
		return false;
	}
	
	public boolean isIPFilterObject(){
		return false;
	}
	
	public boolean isMacFilterObject(){
		return false;
	}
	
	public boolean isInterfaceObject(){
		return false;
	}
	
	public boolean isNetworkInterfaceObject(){
		return false;
	}
	
	public boolean isService(){
		return false;
	}
	
	public boolean isVPLSObject(){
		return false;
	}
	
	public boolean isIESObject(){
		return false;
	}
	
	public boolean isSDPObject(){
		return false;
	}
	
	public boolean isSAPObject(){
		return false;
	}
	
	public boolean isServiceSDPObject(){
		return false;
	}
	
	public boolean isMeshSDP(){
		return false;
	}
	
	public boolean isSpokeSDP(){
		return false;
	}
	
	public boolean isAssociationChild(){
		return false;
	}
	
	public boolean isAssociationParent(){
		return false;
	}
	
	public boolean isBindingParent(){
		return false;
	}
	
	public boolean isBindingChild(){
		return false;
	}
	
	public AlcatelObject getParent(){
		return this.parent;
	}
	
	public boolean isMDAIngressObject(){
		return false;
	}
	
	public boolean isMDAPathManagement(){
		return false;
	}
	
	public boolean isBandwidthPolicy(){
		return false;
	}
}
