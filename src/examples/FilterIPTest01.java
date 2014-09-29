package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.*;;

public class FilterIPTest01 {
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

		for ( Integer filternumber : router.Filters.getIPFilters().keySet()){
				SRIPFilterObject filter = router.Filters.getIPFilter(filternumber);
				System.out.format("Filter: %6d  Description: %-20s \n", filternumber, filter.getDescription());
				
				for ( Integer entryNumber : filter.getEntries().keySet()){
					SRIPFilterEntry entry = filter.getEntry(entryNumber);
					System.out.println("\tEntry: " + entryNumber + " Desc: " + entry.getDescription());
					System.out.println("\t\tProtocol : " + entry.getProtocol());
					System.out.println("\t\tSrc: " + entry.getSourceAddress() + "/" + entry.getSourceMask());
					System.out.println("\t\tDst: " + entry.getDestAddress() + "/" + entry.getDestMask());

				}
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
