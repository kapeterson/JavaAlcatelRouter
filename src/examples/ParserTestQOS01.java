package examples;

import parser.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.qos.*;
public class ParserTestQOS01 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("OK");
		String cfile = "/home/pete/temp/RCSNTXHV0BW010103021LVH01.cfg";
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
