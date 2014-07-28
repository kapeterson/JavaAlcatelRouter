
public class SRSystemConfiguration {
	private String hostName = "";
	
	public SRSystemConfiguration(){
		this.hostName = "";
	}
	
	public void setHostName(String hostname){
		this.hostName = hostname;
		
	}
	
	public String getHostName(){
		return this.hostName;
	}
}
