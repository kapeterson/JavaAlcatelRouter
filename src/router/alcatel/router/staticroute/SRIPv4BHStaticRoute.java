package router.alcatel.router.staticroute;

/**
 * MOdels a ipv4 black hole static route, extends an ipv4 static route
 * @author Kris Peterson
 *
 */
public class SRIPv4BHStaticRoute extends SRIPv4StaticRoute{

	public SRIPv4BHStaticRoute(String network, String mask){
		super(network, mask);
		
	}
	
	public boolean isBlackHoleStaticRoute(){
		return true;
	}
	
	/** get the command issued ont he router to configure the static route **/
	public String getRouteCommand(){
		String cmd = "configure router static-route " + this.getNetwork() + "/" + this.getMask() + " black-hole";
		cmd += " preference " + this.getPreference() + " metric " + this.getMetric(); 
		
		
		if ( this.tag > 0)
			cmd += " tag " + this.getTag();
		
		if ( this.isEnabled())
			cmd += " enable";
		else 
			cmd += " disable ";
		return cmd;
	}
	
	/**
	 * Override the getNextHop()
	 * Returns the configured next-hop of the static route
	 */
	public String getNextHop(){
		return "BH";
	}
}
