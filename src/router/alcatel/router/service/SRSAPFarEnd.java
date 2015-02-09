package router.alcatel.router.service;

public class SRSAPFarEnd {

	protected String name;
	protected String port;
	protected String ip;
	
	public SRSAPFarEnd(String name, String port, String ip){
		this.name = name;
		this.port = port;
		this.ip = ip;
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
