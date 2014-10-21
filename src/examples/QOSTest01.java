package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
public class QOSTest01 {
	
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
			for ( Integer policyNumber : router.QOS.getSAPEgressQOSPolicies().keySet()){
				
				//SAPEgressQOSPolicy policy = router.QOS.getSAPEgressQOSPolicy(policyNumber);
				//System.out.format("%-7s %-10s  AdminUp: %-8s Desc: %25s \n", "PORT", port.getName(), port.isAdminUp() ,port.getDescription());
				System.out.format("SAP Egress policy %d\n", policyNumber);
				
			}
			
			for ( Integer policyNumber : router.QOS.getSAPIngressQOSPolicies().keySet()){
				
				//SAPEgressQOSPolicy policy = router.QOS.getSAPEgressQOSPolicy(policyNumber);
				//System.out.format("%-7s %-10s  AdminUp: %-8s Desc: %25s \n", "PORT", port.getName(), port.isAdminUp() ,port.getDescription());
				System.out.format("SAP Ingress policy %d\n", policyNumber);
				
			}
			
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
