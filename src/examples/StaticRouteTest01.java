package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.staticroute.*;

public class StaticRouteTest01 {
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

		
			
			for ( String routeHash : router.StaticRoute.getIPv4StaticRoutes().keySet()){
				
				//System.out.format("Route :  %s  \n",  routeHash);
				
				SRIPv4StaticRoute route = router.StaticRoute.getIPv4StaticRoute(routeHash);
				//System.out.format("\nRoute %15s/%2s \n", route.getNetwork(), route.getMask());
				System.out.println(route.getRouteCommand());
			}
			
		
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
