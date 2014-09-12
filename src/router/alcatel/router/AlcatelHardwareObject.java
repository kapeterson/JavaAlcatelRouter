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
	public AlcatelHardwareObject(){
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
	
	public void setSerialNumber(String sn){
		serialNumber = sn;
	}
	
	public void setManufactureDate(String md){
		manufactureDate = md;
	}
	
	public void setPartNumber(String pn){
		partNumber = pn;
	}
	
	public String getPartNumber(){
		return partNumber;
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public String getManufactureDate(){
		return manufactureDate;
	}
	
	public void setSNMPIndex(String indx){
		this.snmpIndex = indx;
	}
	
	public String getSNMPIndex(){
		return this.snmpIndex;
	}
}
