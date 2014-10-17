package router.alcatel.router.staticroute;

public class SRIPv4BHStaticRoute extends SRIPv4StaticRoute{

	public SRIPv4BHStaticRoute(String network, String mask){
		super(network, mask);
		
	}
	
	public boolean isBlackHoleStaticRoute(){
		return true;
	}
	
	public String getRouteCommand(){
		String cmd = "static-route " + this.getNetwork() + "/" + this.getMask() + " black-hole";
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
	 */
	public String getNextHop(){
		return "BH";
	}
}
