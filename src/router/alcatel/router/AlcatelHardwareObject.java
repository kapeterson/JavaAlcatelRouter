package router.alcatel.router;


/**
 * Model for Alcatel 7x50 HardwareObjects.  Contains the common components that all
 * pieces of hardware Chassis, CPM, IOM, MDA will contain
 * @author pete
 *
 */
public class AlcatelHardwareObject extends AlcatelObject{
	
	/** Serial number for the hardware **/
	String serialNumber;
	
	/** manufacture date of the hardware.  Likely only valid through SNMP **/
	String manufactureDate;
	
	/** Part Number of the hardware.  Only accessible through SNMP **/
	String partNumber;
	
	/** SNMP Index of the hardware.  Only accessible through SNMP **/
	String snmpIndex;
	
	
	/**
	 * Instantiate the hardware object
	 */
	public AlcatelHardwareObject(AlcatelObjectType otype){
		
		super(otype);
		serialNumber = "";
		manufactureDate = "";
		partNumber = "";
		snmpIndex = null;
		
		this.setObjectType(AlcatelObjectType.NONE);
	}
	
	// overrides isHardwareObject
	public boolean isHardwareObject(){
		return true;
	}
	
	
	/** Set the serial number for the hardware object **/
	public void setSerialNumber(String sn){
		serialNumber = sn;
	}
	
	/** Set the manufaturing date string for the hardware **/
	public void setManufactureDate(String md){
		manufactureDate = md;
	}
	
	/** Set the part number for the hardware **/
	public void setPartNumber(String pn){
		partNumber = pn;
	}
	
	
	/**
	 * Get the Part Number
	 * @return String representing the part number
	 */
	public String getPartNumber(){
		return partNumber;
	}
	
	
	/**
	 * Get the Serial Number
	 * @return String value of the serial numberr
	 */
	public String getSerialNumber(){
		return serialNumber;
	}
	
	/**
	 * Return the manufaturing date of the hardware
	 * @return String representing the manufactured date
	 */
	
	public String getManufactureDate(){
		return manufactureDate;
	}
	
	
	/**
	 * Sets the snmp index for the hardware.  Only valid when parsing with SNMP
	 * @param indx String value of the SNMP Index
	 */
	public void setSNMPIndex(String indx){
		this.snmpIndex = indx;
	}
	
	/**
	 * Returns the snmp index of the hardware.  only valid when parsing with SNMP
	 * @return String value representing the snmp index
	 */
	public String getSNMPIndex(){
		return this.snmpIndex;
	}
}
