package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSDPObject;

public class SDPTest02Associations {
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
	
			
			for ( Integer sdpnum : router.Services.getSDPs().keySet()){
				SRSDPObject sdp = router.Services.getSDP(sdpnum);
				System.out.format("\nSDP :  %d  \nDescription: %-20s\n", sdpnum, sdp.getDescription());
				
				for ( AlcatelObject assoc : sdp.getAssociations()){
					System.out.println("\tAssoc: " + assoc.getObjectType() + "  " + assoc.getName());
				}
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
