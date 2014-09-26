package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.port.SRPortObject;

public class PortTest03_Encap {
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
				System.out.format("PORT: %-10s  ADMINUP: %-6s ENCAP: %-7s DESC: %-50s\n", port.getName(),  port.isAdminUp(), port.ETHERNET.getEncapType(), port.getDescription());
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
