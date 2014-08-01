package alcatel.router;

public class SRChassisObject extends AlcatelHardwareObject {

	
	//protected SRCardConfiguration = null;
	public SRCardConfiguration Cards = null;
	public SRSystemConfiguration System = null;
	public SRChassisObject(){
		this.setObjectType(AlcatelObjectType.CHASSIS);
		
		Cards = new SRCardConfiguration();
		System = new SRSystemConfiguration();
		
	}
	

	public boolean isChassisObject(){
		return true;
	}
}
