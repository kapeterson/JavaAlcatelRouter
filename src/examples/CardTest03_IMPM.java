package examples;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRCardObject;
import router.alcatel.router.card.SRIOMObject;

public class CardTest03_IMPM {
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
			
			for ( Integer key : router.Cards.getCards().keySet()){
				
				SRCardObject card = router.Cards.getCard(key);
				if ( card.isIOMObject()) {
					SRIOMObject iom = (SRIOMObject)card;
					if (iom.isIMM() || iom.isIOM3()){
						//String policyName = ( iom.FP.INGRESS.PATHMGMT.getBandwidthPolicyName())
						System.out.format("Card %-3d  card-type: %-15s  IMPM: %6s POLICY NAME: %s \n", iom.getSlotNumber(), iom.getCardType(), iom.FP.INGRESS.PATHMGMT.isEnabled(), iom.FP.INGRESS.PATHMGMT.getBandwidthPolicyName());

					}
				}
			
				
			}
		} catch ( Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
