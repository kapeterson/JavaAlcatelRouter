
public class SnmpPopulatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("We are starting");
		
		SRSnmpPopulator populator = new SRSnmpPopulator("69.235.125.10", "w7R_cS_lsp");
		populator.populateHardware();
		
		SRChassisObject router = populator.getRouter();
		
		System.out.println("System name is " + router.System.getHostName());
		
		
		for ( Integer key : router.Cards.getCards().keySet()){
			
			SRCardObject card = router.Cards.getCard(key);
			
			System.out.println("Card " + card.getSlotNumber() + " CardType= " + card.getCardType() + " SN: " + card.getSerialNumber() + " PN: " + card.getPartNumber());
		}
		
		
	}
	
}
