package router.alcatel.router.staticroute;
import router.alcatel.router.ip.*;
public class SRIPv4StaticRoute extends SRStaticRouteObject {
	
	//protected byte mask = 0;
	protected IPv4Address nexthop = null;
	protected IPv4Address addr = null;
	
	public SRIPv4StaticRoute(String network, String mask, String nexthop){
		super();
		this.addr = new IPv4Address(network, mask);
		this.nexthop = new IPv4Address(nexthop, "32");
	}
	
	public String getMask(){
		return String.valueOf(this.addr.getNetmask());
	}
	
	public String getNextHop(){
		return this.nexthop.getHostAddress();
	}
	
	public String getNetwork(){
		return String.valueOf(this.addr.getNetwork());
	}
	
	public boolean isIPv4StaticRoute(){
		return true;
	}
	
	public String getRouteCommand(){
		String cmd = "static-route " + this.getNetwork() + "/" + this.getMask() + " next-hop " + this.getNextHop();
		cmd += " preference " + this.getPreference() + " metric " + this.getMetric(); 
		
		
		if ( this.tag > 0)
			cmd += " tag " + this.getTag();
		
		if ( this.isEnabled())
			cmd += " enabled";
		else 
			cmd += " disabled ";
		return cmd;
	}
	
}
