package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRRouterInterface;
import router.alcatel.router.service.SRIESObject;
import router.alcatel.router.service.SRServiceInterface;

public class IESTest02 {
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
	
			
			for ( String intName : router.Router.Interfaces.getInterfaces().keySet()){
				
				SRRouterInterface iface = router.Router.Interfaces.getInterface(intName);
				if ( iface.isServiceInterfaceObject()){
					SRServiceInterface sInt = (SRServiceInterface)iface;
					System.out.println(intName + " parent = " + sInt.getParentService().getServiceNumber());
					
				}
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
