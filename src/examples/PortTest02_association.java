package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.SRPortObject;
import router.alcatel.router.*;

public class PortTest02_association {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("OK");
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
			for ( String key : router.Ports.getPorts().keySet()){
				
				SRPortObject port = router.Ports.getPort(key);
				//System.out.format("%-7s %-10s  AdminUp: %-8s Desc: %25s \n", "PORT", port.getName(), port.isAdminUp() ,port.getDescription());
	
				if ( port.getAssociations().size() > 0){
					System.out.format("Port %s Associations\n", port.getName());
					for ( AlcatelObject assoc : port.getAssociations()){
						System.out.format("\tAssocation %s %s\n", assoc.getObjectType(), assoc.getName());
					}
				}
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
