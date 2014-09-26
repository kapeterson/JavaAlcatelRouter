package router.alcatel.router.port;

import router.alcatel.router.*;

import java.util.ArrayList;

/**
 * Models an SR Physical Port Object
 * @author Kris Peterson
 *
 */
public class SRPortObject extends AlcatelObject implements AssociationChild {
	
	/** Array List containing pointer to every association (interface, sap, lag etc)**/
	protected ArrayList<AlcatelObject> associations = new ArrayList<AlcatelObject>();
	
	/** Boolean values storing shutdown state **/
	protected boolean isShutdown = true;
	
	protected String description = "";
	
	/**
	 *	Instantiate an SRPortObject
	 */
	public SRPortObject(){
		super(AlcatelObjectType.PHYSICALPORT);
		
		
	}
	
	/**
	 * Set the port to a shutdown state
	 */
	public void setAdminDown(){
		this.isShutdown = true;
	}
	
	/**
	 * Set the port to a 'no shutdown' state
	 */
	public void setAdminUp(){
		this.isShutdown = false;
	}
	
	/**
	 * Sets the value of the shutdown
	 * @param shutdown boolean value telling whether or not the port is shut
	 */
	public void setShutdown(boolean shutdown){
		this.isShutdown = shutdown;
	}
	
	
	/**
	 * 
	 * @return boolean value stating whether or not the port is Shutdown
	 */
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	/**
	 * Status of admin up
	 * @return boolean value telling whether or not the port is up
	 */
	public boolean isAdminUp(){
		return !this.isShutdown();
	}
	
	
	/**
	 * Sets description of the port
	 * @param description STring value description for the port
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	
	/**
	 * Gets teh port description
	 * @return String value of the port description
	 */
	
	public String getDescription(){
		return this.description;
	}
	
	
	/**
	 * Returns an array list of every association for the port
	 * @return ArrayList of AlcatelObjects
	 */
	public ArrayList<AlcatelObject> getAssociations(){
		return this.associations;
	}
	
	/**
	 * Creates a pointer to the parent and/or association for the port objectd
	 * if the port is part of a lag or interface an association should be added
	 * @param associationObject Object that is assocated with the port
	 * 
	 */
	public void addAssociation(AlcatelObject associationObject){
		this.associations.add(associationObject);
	}

}
