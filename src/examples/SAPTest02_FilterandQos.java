package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPObject;
import router.alcatel.router.service.SRVPLSObject;

public class SAPTest02_FilterandQos {
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
				//System.out.format("\n\nService Vpls:  %d  Description: %-20s\n", serviceNumber, vpls.getDescription());

				for ( String sapnumber : vpls.getSAPs().keySet()) {
					SRSAPObject sap = vpls.getSAP(sapnumber);
					System.out.format("\tVPLS: %-13s SAP : %-12s  IngressQOS: %-5d  EgressQOS: %-5d   IngressFilter: %-4s %-5d  EgressFilter: %-4s %-5d Description: %-20s\n ", sap.getParentService().getName(), sapnumber, sap.INGRESS.getQosPolicyNumber(), sap.EGRESS.getQosPolicyNumber(), sap.INGRESS.getFilterType(), sap.INGRESS.getFilterNumber(), sap.EGRESS.getFilterType(), sap.EGRESS.getFilterNumber(),  sap.getDescription());
				}

				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
