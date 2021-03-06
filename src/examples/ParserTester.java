package examples;
import java.io.BufferedReader;
import java.io.FileReader;

import parser.manager.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.*;


public class ParserTester {

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
			BufferedReader br = new BufferedReader(new FileReader(cfile));

			String line;
			while ( br.ready()) {
				line = br.readLine();
				pman.ParseLine(line);

				
			}
			br.close();
			SRChassisObject router = pman.getRouter();
			System.out.println("System name is " + router.System.getHostName());
			
			for ( Integer key : router.Cards.getCards().keySet()){
				
				SRCardObject card = router.Cards.getCard(key);
				System.out.format("\nCard %s  card-type %s \n", card.getSlotNumber(), card.getCardType());
				if ( card.isIOMObject()) {
					for ( int i = 1; i <=2;i++){
						SRMDAObject mda = ((SRIOMObject) card).getMDA(i);
						if ( mda != null)
							System.out.format("\tMDA: %d  Type: %s\n", mda.getComplex(), mda.getMDAType());
					}
				}
				
			}
		} catch ( Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
