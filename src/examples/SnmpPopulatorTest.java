package examples;
import alcatel.router.SRCardObject;
import alcatel.router.SRChassisObject;
import alcatel.router.SRSnmpPopulator;
import alcatel.router.SRMDAObject;
import alcatel.router.SRIOMObject;

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
		try {
			long start = System.nanoTime();
			
			SRSnmpPopulator populator = new SRSnmpPopulator(ip, comm);
			populator.populateHardware();
			
			if ( populator.hasConnectionError()){
				System.out.println("CONNECTION ERROR TO HOST ");
				System.exit(1);
			}
			SRChassisObject router = populator.getRouter();
		
			long elapsedTime = ( System.nanoTime() - start) / 1000000;
			System.out.println("Total time = " + elapsedTime + "ms");
			
			System.out.println("System name is " + router.System.getHostName());
			System.out.println("Chassis type is " + router.getChassisType());
			System.out.println("Chassis SN is " + router.getSerialNumber());
			
			System.out.format("%-8s %-20s %-15s %-15s %-15s %n", "CARD", "TYPE", "SN", "PN", "MD");
			System.out.format("%-8s %-20s %-15s %-15s %-15s %n", "--------", "---------------", "---------------", "---------------", "---------------");

			for ( Integer key : router.Cards.getCards().keySet()){
			
				SRCardObject card = router.Cards.getCard(key);
				
				if ( card.isIOMObject() || card.isCPMObject()){
					System.out.format("%-8d %-20s %-15s %-15s %-15s%n", card.getSlotNumber(), card.getCardType().trim(), card.getSerialNumber().trim(), card.getPartNumber().trim(), card.getManufactureDate().trim());

				} 
			}
			
			System.out.println("\n\n\n");
			System.out.format("%-8s %-7s %-25s %-15s %-15s %n", "CARD", "CMPLX", "TYPE", "PN", "SN");
			System.out.format("%-8s %-7s %-25s %-15s %-15s %n", "--------", "-------", "-------------------------", "---------------", "---------------");

			for ( Integer key : router.Cards.getCards().keySet()){
				
				SRCardObject card = router.Cards.getCard(key);
					if (card.isIOMObject()){
						for (int j = 0; j <= 1; j++){
							SRMDAObject tmda = ((SRIOMObject)card).getMDA(j+1);
							if ( tmda != null){
								System.out.format("%-8s %-7s %-25s %-15s %-15s %n", card.getSlotNumber(), (j+1), tmda.getMDAType(), tmda.getPartNumber(), tmda.getSerialNumber());

							}
						}
					}
			}
		
		} catch ( Exception e) {
			System.out.println("We caught an exception " + e.getMessage());
		}
	}
	
}
