package router.alcatel.router.ip;

public abstract class IPAddress {
	
	
	public boolean isIPv4Address(){
		return false;
	}
	
	public boolean isIPv6Address(){
		return false;
	}
	
	public abstract String getHostAddress();
	public abstract int getBitMask();
	public abstract String getNetwork();
	
	
}
