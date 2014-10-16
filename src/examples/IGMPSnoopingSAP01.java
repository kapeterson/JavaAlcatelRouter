package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRIGMPStaticJoin;
import router.alcatel.router.service.SRSAPObject;
import router.alcatel.router.service.SRVPLSObject;

public class IGMPSnoopingSAP01 {
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

				for ( String sapnumber : vpls.getSAPs().keySet()) {
					
					SRSAPObject sap = vpls.getSAP(sapnumber);
					if ( sap.IGMPSNOOPING.getStaticJoins().size() > 0){
						System.out.format("\n\n VPLS:  %d  SAP: %-12s  STATIC JOIN LISTS\n", serviceNumber, sap.getName());


						for ( String group : sap.IGMPSNOOPING.getStaticJoins().keySet()){
							SRIGMPStaticJoin join = sap.IGMPSNOOPING.getStaticJoin(group);
							System.out.format("\tGROUP %-15s   SOURCE: %-15s\n", group , join.getSource() );
						}
					} 
					
					if ( sap.IGMPSNOOPING.isMrouter()){
						System.out.format("\n\n VPLS:  %d  SAP: %-12s  MROUTER: YES\n", serviceNumber, sap.getName());

					}
				}

				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
