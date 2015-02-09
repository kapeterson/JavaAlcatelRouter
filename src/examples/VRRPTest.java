package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRRouterInterface;
import router.alcatel.router.routerinterface.SRVRRPObject;
import router.alcatel.router.service.SRIESObject;

public class VRRPTest {

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
	
			
			System.out.println("Router name is " + router.System.getHostName());
			
			for ( String intName : router.Router.Interfaces.getInterfaces().keySet()){
				SRRouterInterface iface = router.Router.Interfaces.getInterface(intName);
				
				if ( iface.getVRRPObject() != null){
					SRVRRPObject vrrp = iface.getVRRPObject();
					System.out.println("Interface  " + intName + " instance " + vrrp.getID() + " Backup " + vrrp.getBackup());
			
				}
			}
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}

}
