package router.alcatel.router.staticroute;
import router.alcatel.router.ip.*;
public class IPv4StaticRoute extends SRStaticRouteObject {
	
	//protected byte mask = 0;
	protected int nexthop = 0;
	protected IPv4Address addr = null;
	
	public IPv4StaticRoute(String network, String mask){
		super();
		addr = new IPv4Address(network, mask);
	}
	
	public String getMask(){
		return String.valueOf(this.addr.getNetmask());
	}
	
	public String getNextHop(){
		return String.valueOf(this.nexthop);
	}
	
	public String getNetwork(){
		return String.valueOf(this.addr.getNetwork());
	}
	
	public boolean isIPv4StaticRoute(){
		return true;
	}
	
}
