package router.alcatel.router.system;


/**
 * Models the Router System Configuration
 * 
 * @author Kris Peterson
 *
 */
public class SRSystemConfiguration {
	
	/** Router hostname **/
	protected String hostname;
	
	/** configured location **/
	protected String location;
	
	/** configured chassis mode */
	protected String chassismode;
	
	
	/**
	 * Default constructor initializes configuration parameters
	 */
	public SRSystemConfiguration(){
		hostname = "";
		location = "";
		chassismode = "b";
		
	}
	
	/**
	 * 
	 * @return Returns the hostname of the router
	 */
	public String getHostName(){
		return this.hostname;
	}
	
	/**
	 * returns the configured location of the router
	 * @return String value of the configured location
	 */
	public String getLocation(){
		return this.location;
	}
	
	/**
	 * returns the configured chassis mode 
	 * @return string value of the configured chassis mode
	 */
	public String getChassisMode(){
		return this.chassismode;
		
	}
	
	/**
	 * Sets the router's hostname
	 * @param name String value for the name of the router
	 */
	public void setHostName(String name){
		this.hostname = name;
	}
	
	/**
	 * Sets the location string of the router
	 * @param loc String value of the location
	 */
	public void setLocation(String loc){
		this.location = loc;
		
	}
	
	/**
	 * Sets the chassis mode value of the router
	 * @param mode STring value of the chassis mode shoudl be b, c, d, e
	 */
	public void setChassiMode(String mode){
		this.chassismode = mode;
	}
}
