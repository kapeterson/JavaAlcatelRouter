package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRInterfaceBinding;
import router.alcatel.router.routerinterface.SRNetworkInterface;
import router.alcatel.router.routerinterface.SRRouterInterface;

public class NetworkInterfaceTest04_Qos {
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

		for ( String interfaceName : router.Router.Interfaces.getInterfaces().keySet()){
				SRRouterInterface iface = router.Router.Interfaces.getInterface(interfaceName);
				if ( iface.isNetworkInterfaceObject()){
					SRNetworkInterface netInterface = (SRNetworkInterface)iface;
					System.out.format("Interface: %-15s  QOS: %-15d\n", interfaceName, netInterface.getQOSPolicyNumber());
				}	
			

			
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
