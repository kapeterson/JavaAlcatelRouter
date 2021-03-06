package router.alcatel.router;

// object types
// HARDWARE


/**
 * Base AlcatelObject class from which all objects of the router should derive.
 * Allows us to determine the type of object we are dealing with.
 * @author Kris Peterson
 *
 */
public abstract class AlcatelObject {

	
	/** Object name that is used to store things like interface name, sapname, portname etc **/
	private String objectName;
	
	/** Stores the object type value **/
	private AlcatelObjectType objectType;
	
	/** Parent object - e.g  Card is parent for an mda 
	 * or a lag can be parent to a port, vpls can beg parent to SAP.
	 */
	protected AlcatelObject parent = null;
	
	/** Used to store the remark from the MSS **/
	protected String mssRemark = "";
	
	/** Release tag for tracking NR etc if it becomes available **/
	protected int mssReleaseTag = 0;
	
	
	public AlcatelObject(AlcatelObjectType objectType){
		objectName = "";
		this.objectType = objectType;
	}
	
	
	/** sets the name of the object **/
	public void setName(String oName){
		this.objectName = oName;
	}
	
	/**
	 * returns the name of the object
	 * @return String containing name of the object
	 */
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
	
	public boolean isServiceInterfaceObject(){
		return ( this.objectType == AlcatelObjectType.SERVICEINTERFACE);
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
		return (this.objectType == AlcatelObjectType.MESHSDPOBJECT);
	}
	
	public boolean isSpokeSDP(){
		return (this.objectType == AlcatelObjectType.SPOKESDPOBJECT);
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
	
	
	public void setParent(AlcatelObject parent){
		this.parent = parent;
	}
	
	public AlcatelObject getParent(){
		return this.parent;
	}
	
	public boolean isMDAIngressObject(){
		return false;
	}
	
	public boolean isPathManagment(){
		return false;
	}
	
	public boolean isBandwidthPolicy(){
		return false;
	}
	
	public boolean isMDANetworkObject(){
		return false;
	}
	
	public boolean isForwardingPath(){
		return false;
	}
	
	public boolean isPortEthernet(){
		return (this.getObjectType() == AlcatelObjectType.PORTETHERNET);
	}

	public boolean isEthernetNetwork(){
		return (this.getObjectType() == AlcatelObjectType.ETHERNETNETWORK);
	}
	
	public boolean isSAPIngressObject(){
		return (this.objectType == AlcatelObjectType.SAPINGRESSOBJECT);
	}
	
	public boolean isSAPEgressObject(){
		return (this.objectType == AlcatelObjectType.SAPEGRESSOBJECT);
	}
	
	public boolean isSDPIngress(){
		return (this.objectType == AlcatelObjectType.SDPINGRESS);
	}
	
	public boolean isSDPEgress(){
		return (this.objectType == AlcatelObjectType.SDPEGRESS);
	}
	
	public boolean isMPLSPathHop(){
		return (this.objectType == AlcatelObjectType.MPLSPATHHOP);
	}
	
	public boolean isMPLSPath(){
		return (this.objectType == AlcatelObjectType.MPLSPATH);
	}
	
	public boolean isMPLSLSP(){
		return (this.objectType == AlcatelObjectType.MPLSLSP);
	}
	
	public boolean isIPFilterEntry(){
		return (this.objectType == AlcatelObjectType.SRIPFILTERENTRY);
	}
	
	public boolean isIOMFP(){
		return (this.objectType == AlcatelObjectType.IOMFP);
	}
	
	public boolean isLDPInterface(){
		return (this.getObjectType() == AlcatelObjectType.LDPINTERFACE) ;
	}
	
	
	public String getMSSRemark(){
		return this.mssRemark;
	}
	
	public void setMSSRemark(String remark){
		this.mssRemark = remark;
	}
	
	public void setMSSReleaseTag(int tag){
		this.mssReleaseTag = tag;
	}
	
	public int getMSSReleaseTag(){
		return this.mssReleaseTag;
	}
	
	public boolean isSSMTranslation(){
		return ( this.objectType == AlcatelObjectType.IGMPSSM);
	}
	
	public boolean isIGMPStaticJoin(){
		return ( this.objectType == AlcatelObjectType.IGMPSTATICJOIN);
	}
	
	public boolean isIGMPSnooping(){
		return ( this.objectType == AlcatelObjectType.IGMPSNOOPING);
	}
	
	public boolean isOSPFInterface(){
		return ( this.objectType == AlcatelObjectType.OSPFINTERFACE);
	}
	
	public boolean isPortObject(){
		return (this.objectType == AlcatelObjectType.PHYSICALPORT);
	}
	
	public boolean isLagObject(){
		return ( this.objectType == AlcatelObjectType.LAG);
	}
	
	public boolean isForwardingClass(){
		return (this.objectType == AlcatelObjectType.FORWARDINGCLASS);
	}
	
	public boolean isVRRPObject(){
		return (this.objectType == AlcatelObjectType.VRRPOBJECT);
	}
	
	public boolean isSAPQueue(){
		return ( this.objectType == AlcatelObjectType.SRSAPQUEUE);
	}
}
