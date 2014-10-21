package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRIGMPInterface;

public class IGMPInterfaceStaticJoins01 {
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

			
			for ( String ifaceName : router.Router.IGMP.getInterfaces().keySet()){
				SRIGMPInterface iface = router.Router.IGMP.getInterface(ifaceName);
				System.out.println("\nIGMP INT: " + ifaceName);
				
				for ( String groupAddress : iface.getStaticJoins().keySet()){
				
					System.out.println("\t" + groupAddress);
				}
				
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
