package router.alcatel.router.staticroute;

import router.alcatel.router.ip.*;

public class SRIPv4IndirectStaticRoute extends SRIPv4StaticRoute{
	
	public SRIPv4IndirectStaticRoute(String network, String mask, String nextHop){
		super(network, mask);
		this.nexthop = new IPv4Address(network, "32");

	}
	
	/**
	 * Returns the route command used to configure the router on the 7x50
	 */
	public String getRouteCommand(){
		String cmd = "static-route " + this.getNetwork() + "/" + this.getMask() + " indirect " + this.getNextHop();
		cmd += " preference " + this.getPreference() + " metric " + this.getMetric(); 
		
		
		if ( this.tag > 0)
			cmd += " tag " + this.getTag();
		
		if ( this.isEnabled())
			cmd += " enable";
		else 
			cmd += " disable ";
		return cmd;
	}
	
	public boolean isIndirectRoute(){
		return true;
	}
	
}
