package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRIPFilterEntry;
import router.alcatel.router.filter.SRIPFilterObject;

public class IPFilterMatchTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		if (args.length < 1){
			System.out.println("Error you must supply path to configuration file");
			return;
		}		
		
		String cfile = args[0];
		System.out.println("Going to parse " + cfile);
		

		Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

		String srcIP = "68.94.156.1";
		String dstIP = "0.0.0.0";
		
		String protocol = "udp";
		int srcPort = 1023;
		int dstPort = 69;
		
		try {
			pman.ParseConfig(cfile);
			SRChassisObject router = pman.getRouter();

			
			System.out.println("Hostname " + router.System.getHostName());
			
			for ( Integer filterNuber : router.Filters.getIPFilters().keySet()){
				
				System.out.println("\n\nFilter " + filterNuber);
				SRIPFilterObject filter = router.Filters.getIPFilter(filterNuber);
				System.out.println("\tDefault Action = " + filter.getDefaultAction());
				

				for ( Integer entryNumber : filter.getEntries().keySet()){
					if ( entryNumber == 3656){
						//System.out.println("Pause");
					}
					SRIPFilterEntry ipEntry = filter.getEntry(entryNumber);
					
					if ( ipEntry.isIPMatch(srcIP, dstIP) ){
						
			
						//if ( ipEntry.isProtocolMatch(protocol) ){
							
							//if ( ipEntry.isSrcPortMatch(srcPort) && ipEntry.isDstPortMatch(dstPort)) {
								System.out.format("\tMatch on Filter %-6d   Entry %-5d  Protocl: %-8s\n", filterNuber, entryNumber, protocol);
								break;
							//}

						//}
					}
					
				}
				
				
			}
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
