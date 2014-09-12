package router.alcatel.router;



public class AlcatelHardwareObject extends AlcatelObject{
	
	String serialNumber;
	String manufactureDate;
	String partNumber;
	String snmpIndex;
	
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
