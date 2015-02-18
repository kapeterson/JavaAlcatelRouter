package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.ForwardingClass;
import router.alcatel.router.qos.SAPEgressQOSPolicy;
import router.alcatel.router.qos.SRSAPQueue;

public class QosSAPEgress01Test {
	public static void main(String[] args) {

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
			
			for ( Integer policyNumber : router.QOS.getSAPEgressQOSPolicies().keySet()){
				System.out.println("\nEgress SAP QOS Policy : " + policyNumber);
				SAPEgressQOSPolicy tpolicy = router.QOS.getSAPEgressQOSPolicy(policyNumber);
				
				for ( Integer qnum : tpolicy.getQueues().keySet()){
					SRSAPQueue q = tpolicy.getQueue(qnum);
					
					System.out.println("    Queue: " + qnum );
					System.out.println("       Rate: " + q.getRate() + " cir " + q.getCIR());
					System.out.println("       MBS:  " + q.getMBS());
					System.out.println("       CBS:  " + q.getCBS() + "\r\n");
					
				}
				
				for ( String fcname : tpolicy.getForwardingClasses().keySet()){
					ForwardingClass myfc = tpolicy.getForwardingClass(fcname);
					System.out.println("    FC: " + fcname  + " Queue : " + myfc.getQueue());
				}
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}	
	}
	
	

}
