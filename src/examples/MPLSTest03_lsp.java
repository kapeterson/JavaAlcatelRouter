package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.mpls.SRMplsLSP;

public class MPLSTest03_lsp {
	public static void main(String[] args){
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

		
			
			for ( String lspName : router.Router.MPLS.getLSPs().keySet()){
				
				SRMplsLSP lsp = router.Router.MPLS.getLSP(lspName);
				System.out.format("\nMPLS LSP:  %-12s  TO: %-12s\n",  lspName, lsp.getToAddress());
				System.out.format("\tPrimary   : %-8s\n", lsp.getPrimaryPath());
				System.out.format("\tSecondary : %-8s\n", lsp.getSecondaryPath());

			
			}
			
		
		} catch ( Exception e) {
			System.out.println("Run test error : " + e.getMessage());
		}
	}
}
