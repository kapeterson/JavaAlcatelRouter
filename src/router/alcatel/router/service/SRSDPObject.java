package router.alcatel.router.service;

import java.util.ArrayList;

import router.alcatel.router.AlcatelObject;
import router.alcatel.router.AlcatelObjectType;

public class SRSDPObject extends SRServiceObject{
	
	
	/** Array List containing pointer to every association (interface, vpls, etc)**/
	protected ArrayList<AlcatelObject> associations = new ArrayList<AlcatelObject>();
	
	
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
}
