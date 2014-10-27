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

		String srcIP = "155.179.73.1";
		String dstIP = "69.235.114.151";
		String protocol = "tcp";
		int portNumber = 0;
		
		try {
			pman.ParseConfig(cfile);
			SRChassisObject router = pman.getRouter();

			
			System.out.println("Hostname " + router.System.getHostName());
			
			for ( Integer filterNuber : router.Filters.getIPFilters().keySet()){
				
				System.out.println("Filter " + filterNuber);
				SRIPFilterObject filter = router.Filters.getIPFilter(filterNuber);
				
				for ( Integer entryNumber : filter.getEntries().keySet()){
					
					SRIPFilterEntry ipEntry = filter.getEntry(entryNumber);
					
					if ( ipEntry.isIPMatch(srcIP, dstIP) && ipEntry.isProtocolMatch(protocol)){
						
						System.out.format("Match on Filter %-6d   Entry %-5d  Protocl: %-8s\n", filterNuber, entryNumber, protocol);
						break;
					}
					
				}
				
				
			}
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
