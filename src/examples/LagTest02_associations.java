package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.lag.SRLagObject;

public class LagTest02_associations {
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

		for ( Integer lagNumber : router.Lags.getLags().keySet()){
				
				SRLagObject lag = router.Lags.getLag(lagNumber);
				//System.out.format("%-7s %-10s  AdminUp: %-8s Desc: %25s \n", "PORT", port.getName(), port.isAdminUp() ,port.getDescription());
				System.out.format("\nLag %d\n", lag.getLagNumber());

				for ( AlcatelObject assoc : lag.getAssociations()){
					System.out.format("\tAssocation %s %s\n", assoc.getObjectType(), assoc.getName());
				}
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
