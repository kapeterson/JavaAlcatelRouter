package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.lag.SRLagObject;

public class LagTest01 {
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
				System.out.println("\tAdmin Up: " + lag.isAdminUp());
				System.out.println("\tDescription: " + lag.getDescription());
				for ( String portName : lag.getPorts().keySet()){
					System.out.println("\t" + portName);
				}
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
