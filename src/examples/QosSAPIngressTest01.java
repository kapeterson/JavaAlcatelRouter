package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.ForwardingClass;
import router.alcatel.router.qos.SAPEgressQOSPolicy;
import router.alcatel.router.qos.SAPIngressQOSPolicy;

public class QosSAPIngressTest01 {

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
			System.out.println("Parsed name = "  + router.System.getHostName());
			
			for ( Integer policyNumber : router.QOS.getSAPIngressQOSPolicies().keySet()){
				System.out.println("Ingress Policy : " + policyNumber);
				SAPIngressQOSPolicy tpolicy = router.QOS.getSAPIngressQOSPolicy(policyNumber);
				
				for ( String fcname : tpolicy.getForwardingClasses().keySet()){
					ForwardingClass myfc = tpolicy.getForwardingClass(fcname);
					System.out.println("     FC: " + fcname  + " Queue : " + myfc.getQueue());
				}
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}	
	}

}
