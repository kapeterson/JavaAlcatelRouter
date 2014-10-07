package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.routerinterface.SRInterfaceBinding;
import router.alcatel.router.service.SRIESObject;
import router.alcatel.router.service.SRSAPObject;
import router.alcatel.router.service.SRServiceInterface;
import router.alcatel.router.service.SRServiceSDPObject;
import router.alcatel.router.service.SRVPLSObject;

public class ServiceSDPTest01 {
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
	
			
			for ( Integer serviceNumber : router.Services.getVPLSs().keySet()){
				SRVPLSObject vpls = router.Services.getVPLS(serviceNumber);
				//System.out.format("\n\nService Vpls:  %d  Description: %-20s\n", serviceNumber, vpls.getDescription());

				for ( String sdpnumber : vpls.getSDPs().keySet()) {
					SRServiceSDPObject sdp = vpls.getSDP(sdpnumber);
					System.out.format("\tVPLS: %-13d SDP : %-12s IngressFilter: %-5s EgressFilter: %-5s  \n ", serviceNumber, sdpnumber, sdp.INGRESS.getFilterNumber(), sdp.EGRESS.getFilterNumber() );
				}

				
			}
			
			for ( Integer iesNumber : router.Services.getIESs().keySet()){
				SRIESObject ies = router.Services.getIES(iesNumber);
				
				for ( String intName : ies.getInterfaces().keySet()){
					SRServiceInterface iface = ies.getInterface(intName);
					
					SRInterfaceBinding binding = iface.getBinding();
					if ( binding.getBindingObject().isServiceSDPObject()) {
						System.out.format("IES: %-12d  INT: %-12s  SDP: %-8s Ing. Filter:  %-12s Egr. Filter: %-12s\n", iesNumber, intName, binding.getBindingObject().getName(), ((SRServiceSDPObject)binding.getBindingObject()).INGRESS.getFilterNumber(), ((SRServiceSDPObject)binding.getBindingObject()).EGRESS.getFilterNumber());
					}
				}
				//System.out.format("IES: %-12d\n", iesNumber);
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
