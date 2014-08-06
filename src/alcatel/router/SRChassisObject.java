package alcatel.router;

public class SRChassisObject extends AlcatelHardwareObject {

	
	//protected SRCardConfiguration = null;
	public SRCardConfiguration Cards = null;
	public SRSystemConfiguration System = null;
	public String chassisType;
	
	public SRChassisObject(){
		this.setObjectType(AlcatelObjectType.CHASSIS);
		
		Cards = new SRCardConfiguration();
		System = new SRSystemConfiguration();
		chassisType = "NA";
	}
	
	public String getChassisType(){
		return chassisType;
	}
	
	public void setChassisType(String chassis){
		chassisType = chassis;
	}
	
	public boolean isChassisObject(){
		return true;
	}
}
