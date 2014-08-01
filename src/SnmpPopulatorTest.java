
public class SnmpPopulatorTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("We are starting");
		
		if (args.length < 2){
			System.out.println("Error you must supply ip /and community");
			return;
		}
		String ip = args[0];
		String comm = args[1];
		
		System.out.println("IP = " + ip);
		SRSnmpPopulator populator = new SRSnmpPopulator(ip, comm);
		populator.populateHardware();
		
		SRChassisObject router = populator.getRouter();
		
		System.out.println("System name is " + router.System.getHostName());
		
		
		for ( Integer key : router.Cards.getCards().keySet()){
			
			SRCardObject card = router.Cards.getCard(key);
			
			System.out.println("Card " + card.getSlotNumber() + " CardType= " + card.getCardType() + " SN: " + card.getSerialNumber() + " PN: " + card.getPartNumber());
		}
		
		
	}
	
}
