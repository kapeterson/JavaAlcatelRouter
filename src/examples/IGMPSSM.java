package examples;


import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.igmp.SRSSMTranslation;


public class IGMPSSM {
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

			
			for ( SRSSMTranslation ssm : router.Router.IGMP.getTranslations()){
				System.out.println(ssm.getStart() + " - " + ssm.getEnd());
				System.out.println("\t"+ssm.getSource());
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
