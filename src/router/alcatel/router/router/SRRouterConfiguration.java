package router.alcatel.router.router;

import router.alcatel.router.bgp.SRBGPConfiguration;
import router.alcatel.router.igmp.SRIGMPConfiguration;
import router.alcatel.router.ldp.SRLDPConfiguration;
import router.alcatel.router.mpls.SRMplsConfiguration;
import router.alcatel.router.ospf.SROSPFConfiguration;
import router.alcatel.router.pim.SRPIMConfiguration;
import router.alcatel.router.routerinterface.SRInterfaceConfiguration;
import router.alcatel.router.rsvp.SRRSVPConfiguration;
import router.alcatel.router.staticroute.SRStaticRouteConfiguration;

/**
 * Models the router coniguration component ont he 7x50.  It provides access to the
 * Interface, OSPF, PIM, IGMP, MPLS, LDP, RSVP, BGP and STatic Route config
 * @author Kris Peterson
 *
 */
public class SRRouterConfiguration {

	/** Expose the Interface configuration **/
	public SRInterfaceConfiguration Interfaces = null;
	
	/** Access to the OSPF Configuration **/
	public SROSPFConfiguration OSPF = null;
	
	/** Access to the PIM Configuration **/
	public SRPIMConfiguration PIM = null;
	
	/** Access to the IGMP Configuration **/
	public SRIGMPConfiguration IGMP = null;
	
	/** Access to the MPLS Configuration **/
	public SRMplsConfiguration MPLS = null;
	
	/** Access to the LDP Configuration **/
	public SRLDPConfiguration LDP = null;
	
	/** Accesss to the RSVP Configuration **/
	public SRRSVPConfiguration RSVP = null;
	
	/** Access to BGP Configuration **/
	public SRBGPConfiguration BGP = null;
	
	/** Access to Static Route Configuration **/
	public SRStaticRouteConfiguration StaticRoutes = null;
	
	public SRRouterConfiguration (){
		this.Interfaces = new SRInterfaceConfiguration();
		this.OSPF = new SROSPFConfiguration();
		this.PIM = new SRPIMConfiguration();
		this.IGMP =  new SRIGMPConfiguration();
		this.MPLS = new SRMplsConfiguration();
		this.RSVP = new SRRSVPConfiguration();
		this.BGP = new SRBGPConfiguration();
		this.StaticRoutes = new SRStaticRouteConfiguration();
		this.LDP = new SRLDPConfiguration();
		
	}
}
