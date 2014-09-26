package router.alcatel.router.service;

import java.util.ArrayList;

import router.alcatel.router.*;
public class SRServiceSDPObject extends AlcatelObject implements BindingChild, AssociationChild {

	protected Integer sdpNumber = 0;
	protected Integer vcid = 0;
	
	/** Array List containing pointer to every association (interface, ies, vpls, ies)**/
	protected ArrayList<AlcatelObject> associations = new ArrayList<AlcatelObject>();
	
	public SRServiceSDPObject(AlcatelObjectType sdpType, String sdpName){
		super(sdpType);
		
		
		// verify object type
		if ( sdpType != null && ( sdpType != AlcatelObjectType.MESHSDPOBJECT && sdpType != AlcatelObjectType.SPOKESDPOBJECT))
			System.out.println("ERROR: Invalid object type in sdp instantiation " + sdpType);
		
		
		if ( sdpType != null)
			this.setObjectType(sdpType);
		else
			this.setObjectType(AlcatelObjectType.SERVICESDPOBJECT);
		
		this.setName(sdpName);
		String[] sdp = sdpName.split(":");
		this.sdpNumber = Integer.parseInt(sdp[0]);
		this.vcid = Integer.parseInt(sdp[1]);
		
	}
	
	public String getSDPName(){
		return sdpNumber + ":" + vcid;
	}
	
	public ArrayList<AlcatelObject> getAssociations(){
		return this.associations;
	}
	
	public void addAssociation(AlcatelObject assoc){
		this.associations.add(assoc);
	}
	
	@Override
	public boolean isServiceSDPObject(){
		return true;
	}
	
	@Override
	public boolean isBindingChild(){
		return true;
	}
	
	@Override 
	public boolean isAssociationChild(){
		return true;
	}


}
