package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRInterfaceBinding;
import router.alcatel.router.routerinterface.SRRouterInterface;
import router.alcatel.router.service.SRSAPObject;

public class IESInterfaceTest02_sapinfo {
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
				SRInterfaceBinding binding = iface.getBinding();
				if ( binding != null && binding.getBinding().isSAPObject()) {
					SRSAPObject sap = (SRSAPObject)binding.getBinding();
					System.out.format("Interface: %-15s  SAP Binding: %-10s I. QOS: %-5d E. QOS: %-5d I. Filter: %-5d E. Filter: %-5d \n", interfaceName,  sap.getName(), sap.EGRESS.getQosPolicyNumber(), binding.getTag(), sap.INGRESS.getQosPolicyNumber(), sap.INGRESS.getFilterNumber(), sap.EGRESS.getFilterNumber());

				}
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
