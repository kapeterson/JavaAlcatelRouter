package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.*;
public class ParserTestInterface01 {
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

		for ( String  iname : router.Interface.getInterfaces().keySet()){
				SRRouterInterface iface = router.Interface.getInterface(iname);
				//System.out.println("Interface " + iname + " addr = " + iface.getIPv4Address().toString().split("/")[1]);
				System.out.println("Interface " + iname + " addr = " + iface.getIPv4HostAddress());
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
