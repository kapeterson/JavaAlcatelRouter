package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRIESObject;

public class IESTest01 {
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
	
			
			for ( Integer serviceNumber : router.Services.getIESs().keySet()){
				SRIESObject ies = router.Services.getIES(serviceNumber);
				System.out.format("Service ies:  %d  Description: %-20s\n", serviceNumber, ies.getDesription());
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
