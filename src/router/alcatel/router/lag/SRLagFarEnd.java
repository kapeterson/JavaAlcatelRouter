package router.alcatel.router.lag;

public class SRLagFarEnd {
	public String name;
	public String port;
	public String lag;
	
	public SRLagFarEnd(String feName, String lag, String port){
		this.name = feName;
		this.lag =  lag;
		this.port = port;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPort(){
		return this.port;
	}
	
	public String getLag(){
		return this.lag;
	}
}
