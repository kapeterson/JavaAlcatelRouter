package router.alcatel.router.system;

public class SRSystemConfiguration {
	protected String hostname;
	protected String location;
	protected String chassismode;
	
	public SRSystemConfiguration(){
		hostname = "";
		location = "";
		chassismode = "b";
		
	}
	
	public String getHostName(){
		return this.hostname;
	}
	
	public String getLocation(){
		return this.location;
	}
	
	public String getChassisMode(){
		return this.chassismode;
		
	}
	
	public void setHostName(String name){
		this.hostname = name;
	}
	
	public void setLocation(String loc){
		this.location = loc;
		
	}
	
	public void setChassiMode(String mode){
		this.chassismode = mode;
	}
}
