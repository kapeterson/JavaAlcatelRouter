package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPObject;
import router.alcatel.router.service.SRVPLSObject;

public class SAPTest01_vplssapcount {
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
	
			System.out.println("System ip = " + router.Router.Interfaces.getInterface("system").getIPv4HostAddress());
			for ( Integer serviceNumber : router.Services.getVPLSs().keySet()){
				SRVPLSObject vpls = router.Services.getVPLS(serviceNumber);
				System.out.format("VPLS: %-15d    SAP Count: %-5d\n", serviceNumber, vpls.getSAPs().size());
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
