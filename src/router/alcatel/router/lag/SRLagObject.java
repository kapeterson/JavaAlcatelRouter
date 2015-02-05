package router.alcatel.router.lag;
import router.alcatel.router.*;
import router.alcatel.router.port.*;

import java.util.ArrayList;
import java.util.Hashtable;
/**
 * Models SR 7x50 Lag 
 * @author Kris Peterson
 *
 */
public class SRLagObject extends AlcatelObject implements SRInterfaceBindingObject, AssociationChild{
	
	/** Array List containing pointer to every association (interface, sap, lag etc)**/
	protected ArrayList<AlcatelObject> associations = new ArrayList<AlcatelObject>();
	
	
	/** Lag description **/
	protected String description = "";
	
	/** Admin status of the lag **/
	protected boolean isShutdown = true;
	
	/** Lag number */
	protected Integer lagnumber = 0;
	
	/** Hashtable of ports, key = portname value = port object **/
	protected Hashtable<String, SRPortObject> ports = null;
	

	/** lag encapsulation type **/
	protected String encapsulation = "null";
	
	protected String mode = "network";
	
	protected SRLagFarEnd farend = null;
	
	public SRLagObject(){
		super(AlcatelObjectType.LAG);
		this.ports = new Hashtable<String, SRPortObject>();
		this.lagnumber = 0;
	}
	
	public SRLagObject(Integer lagNumber){
		super(AlcatelObjectType.LAG);
		this.ports = new Hashtable<String, SRPortObject>();
		this.lagnumber = lagNumber;
		this.setName(String.valueOf(lagNumber));
	}
	
	public void setDescription(String description){
		this.description = description;
	}

	/**
	 * 
	 * @return Returns String lag description
	 */
	public String getDescription(){
		return this.description;
	}
	
	/**
	 * Adds port to the lag
	 * @param port SRPortObject to add to the lag
	 */
	public void addPort(SRPortObject port){
		ports.put(port.getName(), port);
	}
	
	
	/**
	 * Returns the port hash for the lag key=portname value=portObject
	 * @return hashtable with portname nad port object
	 */
	public Hashtable<String, SRPortObject> getPorts(){
		return this.ports;
	}
	
	
	/**
	 * Sets the number of the lag
	 * @param lagnumber Integer lag number
	 */
	public void setLagNumber(Integer lagnumber){
		this.lagnumber = lagnumber;
		this.setName(String.valueOf(lagnumber));
	}
	
	/**
	 * 
	 * @return Integer lag number
	 */
	public Integer getLagNumber(){
		return this.lagnumber;
	}
	
	/**
	 * Forces the state of the lag to admin down / shutdown
	 */
	public void adminDown(){
		this.isShutdown = true;
	}
	
	/**
	 * Forces the state of the lag to admin up / no shutdown
	 */
	public void adminUp(){
		this.isShutdown = false;
	}
	
	/**
	 * 
	 * @return is the lag shutdown
	 */
	public boolean isShutdown(){
		return this.isShutdown;
	}
	
	/**
	 * 
	 * @return boolean is the lad admin up a.k.a.  'no shutdown'
	 */
	public boolean isAdminUp(){
		return !this.isShutdown();
	}
	
	/**
	 * Returns an array list of every association for the lag
	 * @return ArrayList of AlcatelObjects
	 */
	public ArrayList<AlcatelObject> getAssociations(){
		return this.associations;
	}
	
	/**
	 * Creates a pointer to the parent and/or association for the lag object
	 * if the lag is part of an interface or sap an association should be added
	 * @param associationObject Object that is assocated with the lag
	 * 
	 */
	public void addAssociation(AlcatelObject associationObject){
		this.associations.add(associationObject);
	}
	
	
	
	/** Set the encapsulation type on the lag **/
	public void setEncapsulation(String encapType){
		if ( SRData.PORT_ENCAP_TYPES.contains(encapType)){
			this.encapsulation = encapType;
		} else {
			System.out.println("ERROR: Could not set encapsulation to " + encapType + " on lag " + this.getLagNumber());
		}
	}
	
	
	/** Get the configured encapsulation type of the lag **/
	public String getEncapsulation(){
		return this.encapsulation;
	}
	
	
	/** Set the ethernet mode of the lag ( access/network ) **/
	public void setMode(String mode){
		if ( SRData.ETHERNET_MODES.contains(mode))
			this.mode = mode;
		else{
			System.out.println("ERROR setting mode to " + mode + " on port ");
		}
	}
	
	
	/** Return the configured mode of the lag **/
	public String getMode(){
		return this.mode;
	}
	
	
	@Override
	public boolean isAssociationChild(){
		return true;
	}
	
	@Override
	public boolean isBindingChild(){
		return true;
	}
	
	public SRLagFarEnd getFarEnd(){
		return this.farend;
	}
	
	public void setFarEnd(SRLagFarEnd farendlag){
		this.farend = farendlag;
	}
}
