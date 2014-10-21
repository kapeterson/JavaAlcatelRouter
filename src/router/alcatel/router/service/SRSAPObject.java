package router.alcatel.router.service;

import router.alcatel.router.*;

/**
 * MOdels a SAP Object on the 7x50
 * @author Kris Peterson
 *
 */
public class SRSAPObject extends AlcatelObject implements SRInterfaceBindingObject, SRServiceChild {

	/** Name/number of the sap **/
	protected String sapName = "";
	
	/** configured sap description **/
	protected String description = "";
	
	/** Parent service for the SAP ( IES/VPLS/EPIPE etc) **/
	protected AlcatelObject parentService = null;
	
	/** access to the SAP INGRESS component **/
	public SRSAPIngress INGRESS = new SRSAPIngress();
	
	/** Access to othe SAP Egress component **/
	public SRSAPEgress EGRESS = new SRSAPEgress();
	
	/** Access to the IGMP Snooping component **/
	public SRIGMPSnooping IGMPSNOOPING = new SRIGMPSnooping();
	
	public SRSAPObject(String sapname, AlcatelObject parentService){
		super(AlcatelObjectType.SAPOBJECT);
		this.setName(sapname);
		this.sapName = sapname;
		this.description = "";
		this.parentService = parentService;
	}
		
	/** Get the name of the sap **/
	public String getSAPName(){
		return this.sapName;
	}
	
	
	/** get the configured sap description **/
	public String getDescription(){
		return this.description;
	}
	
	
	/** Set the description of the sap **/
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
	
	/** Get the parent service object **/
	public AlcatelObject getParentService(){
		return this.parentService;
	}
	
	

	
}
