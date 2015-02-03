package router.alcatel.router.port;

public class SRPortFarEnd {
	
	public String name;
	public String port;
	public String ip;
	
	public SRPortFarEnd(String feName, String fePort, String feIP){
		this.name = feName;
		this.port =  fePort;
		this.ip = feIP;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPort(){
		return this.port;
	}
	
	public String getIP(){
		return this.ip;
	}
	
}
