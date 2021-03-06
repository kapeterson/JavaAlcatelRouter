package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.policy.*;

public class PolicyCommunityTest01 {
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

		
		for ( String commname : router.Policy.getCommunities().keySet()){
				SRPolicyCommunity comm = router.Policy.getCommunity(commname);
				
				System.out.format("Community %s \n", commname);
				
				for ( String member : comm.getMembers()){
					System.out.println("\t" + member);
				}

				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
