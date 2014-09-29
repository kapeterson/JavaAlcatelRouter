package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsHop;
import router.alcatel.router.mpls.SRMplsPath;

public class MPLSTest02_Path {
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

		
			
			for ( String pname : router.Router.MPLS.getPaths().keySet()){
				
				System.out.format("MPLS Path:  %s  \n",  pname);
				SRMplsPath path = router.Router.MPLS.getPath(pname);
				
				for ( Integer hopnumber : path.getHops().keySet()){
					SRMplsHop hop = path.getHop(hopnumber);
					System.out. format("\tHop : %-5d  Address: %-9s\n", hopnumber, hop.getAddress());
				}
			
			}
			
		
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
