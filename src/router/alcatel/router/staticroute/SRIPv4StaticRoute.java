package router.alcatel.router.staticroute;
import router.alcatel.router.ip.*;

/**
 * Models an ipv4 static route on the 7x50.  All route types next-hop, indirect, black-hole will extend this
 * @author kp109p
 *
 */
public class SRIPv4StaticRoute extends SRStaticRouteObject {
	
	/** Configured next hop address **/
	protected IPv4Address nexthop = null;
	
	/** configured network address **/
	protected IPv4Address addr = null;
	
	public SRIPv4StaticRoute(String network, String mask){
		super();
		this.addr = new IPv4Address(network, mask);
		//this.nexthop = new IPv4Address(nexthop, "32");
	}
	
	/** get the mask configured on the router **/
	public String getMask(){
		return String.valueOf(this.addr.getNetmask());
	}
	
	/** Get the configured next hop on the router **/
	public String getNextHop(){
		return this.nexthop.getHostAddress();
	}
	
	/** Get the network address of the configured router **/
	public String getNetwork(){
		return String.valueOf(this.addr.getNetwork());
	}
	
	
	public boolean isIPv4StaticRoute(){
		return true;
	}
	
	/** Get the configuration command to add the route.  Child class will override to create the correct command **/
	public String getRouteCommand(){
		return "";
	}

	
}
