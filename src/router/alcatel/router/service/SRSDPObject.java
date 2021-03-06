package router.alcatel.router.service;

import java.util.ArrayList;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.AssociationChild;

/**
 * Models a Service SDP Object
 * @author KRis Peterson
 *
 */
public class SRSDPObject extends SRServiceObject implements AssociationChild {
	
	
	/** Array List containing pointer to every association (interface, vpls, etc)**/
	protected ArrayList<AlcatelObject> associations = new ArrayList<AlcatelObject>();
	
	/** Configured Delivery of the SDP **/
	protected SRSDPDelivery delivery = SRSDPDelivery.gre;
	
	

	public SRSDPObject(Integer sdpnumber){
		super(sdpnumber);
		this.setName(sdpnumber.toString());
		this.setObjectType(AlcatelObjectType.SDPOBJECT);
	
	}
	
	public boolean isSDPObject(){
		return true;
	}
	
	
	/**
	 * Add an association to the sdp object
	 * @param obj The AlcatelObject bound to the sdp
	 */
	public void addAssociation(AlcatelObject obj){
		this.associations.add(obj);
	}
	
	
	/**
	 * gets the list of AlcatelObject Associations
	 * @return an array list of associations that can be iterated
	 */
	public ArrayList<AlcatelObject> getAssociations(){
		return this.associations;
	}
	
	public boolean isAssociationChild(){
		return true;
	}
	
	/** Get the configured delivery of the SDP **/
	public SRSDPDelivery getDelivery(){
		return this.delivery;
	}
	
	
	/** Set the delivery method of the SDP **/
	public void setDelivery(SRSDPDelivery delivery){
		this.delivery = delivery;
	}
}
