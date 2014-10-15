package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPObject;
import router.alcatel.router.service.SRSDPObject;
import router.alcatel.router.service.SRServiceSDPObject;
import router.alcatel.router.service.SRVPLSObject;

public class IGMPSnoopingSDP01 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 1){
			System.out.println("Error you must supply path to configuration file");
			return;
		}		
		
		String cfile = args[0];
		System.out.println("Going to parse " + cfile);

		Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

		try {
			pman.ParseConfig(cfile);
			SRChassisObject router = pman.getRouter();
	
			
			for ( Integer serviceNumber : router.Services.getVPLSs().keySet()){
				SRVPLSObject vpls = router.Services.getVPLS(serviceNumber);

				for ( String sdpNumber : vpls.getSDPs().keySet()) {
					
					SRServiceSDPObject sdp = vpls.getSDP(sdpNumber);
					if ( sdp.IGMPSNOOPING.getStaticJoins().size() > 0){
						System.out.format("\n\n VPLS:  %d  SDP: %-12s  STATIC JOIN LISTS\n", serviceNumber, sdp.getName());

						for ( String group : sdp.IGMPSNOOPING.getStaticJoins().keySet()){
							System.out.format("\tGROUP %-15s   SOURCE: %-15s\n", group ,sdp.IGMPSNOOPING.getSource(group) );
						}
					} 
					
					if ( sdp.IGMPSNOOPING.isMrouter()){
						System.out.format("\n\n VPLS:  %d  SDP: %-12s  MROUTER: YES\n", serviceNumber, sdp.getName());

						//System.out.println("SDP : " + sdp.getName() + "   MROUTER: YES\n");
					}
				}

				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
