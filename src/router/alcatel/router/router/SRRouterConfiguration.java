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

public class SRRouterConfiguration {

	public SRInterfaceConfiguration Interfaces = null;
	public SROSPFConfiguration OSPF = null;
	public SRPIMConfiguration PIM = null;
	public SRIGMPConfiguration IGMP = null;
	public SRMplsConfiguration MPLS = null;
	public SRLDPConfiguration LDP = null;
	public SRRSVPConfiguration RSVP = null;
	public SRBGPConfiguration BGP = null;
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
		
	}
}
