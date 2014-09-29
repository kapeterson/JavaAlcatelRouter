package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.bgp.SRBGPGroup;

public class BGPTest01 {
	public static void main(String[] args){
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

		
			
			for ( String groupName : router.BGP.getGroups().keySet()){
				
				System.out.format("BGP Group:  %s  \n",  groupName);
				SRBGPGroup group = router.BGP.getGroup(groupName);
				
				for ( String neighbor : group.getNeighbors()){
					System.out.println("\tNeighbor: " + neighbor);
				}
			
			}
			
		
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
