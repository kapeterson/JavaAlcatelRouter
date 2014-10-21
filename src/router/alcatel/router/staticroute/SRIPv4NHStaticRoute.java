package router.alcatel.router.staticroute;
import router.alcatel.router.ip.*;

/**
 * MOdels a 'next-hop' static route on the 7x50
 * @author kp109p
 *
 */
public class SRIPv4NHStaticRoute extends SRIPv4StaticRoute{
	
	public SRIPv4NHStaticRoute(String network, String mask, String nexthop){
		super(network, mask);
		this.nexthop = new IPv4Address(nexthop, "32");
	}
	
	/**
	 * Returns the route command used to configure the router on the 7x50
	 */
	public String getRouteCommand(){
		String cmd = "configure router static-route " + this.getNetwork() + "/" + this.getMask() + " next-hop " + this.getNextHop();
		cmd += " preference " + this.getPreference() + " metric " + this.getMetric(); 
		
		
		if ( this.tag > 0)
			cmd += " tag " + this.getTag();
		
		if ( this.isEnabled())
			cmd += " enable";
		else 
			cmd += " disable ";
		return cmd;
	}
	
	public boolean isNextHopStaticRoute(){
		return true;
	}
}
