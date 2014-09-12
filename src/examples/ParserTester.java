package examples;
import java.io.BufferedReader;
import java.io.FileReader;

import parser.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRCardObject;

public class ParserTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("OK");
		String cfile = "/home/pete/temp/RCSNTXHV0BW010103021LVH01.cfg";
		System.out.println("Going to parse " + cfile);
		

		Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

		try {
			BufferedReader br = new BufferedReader(new FileReader("/home/pete/temp/RCSNTXHV0BW010103021LVH01.cfg"));

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
				System.out.format("Card %s  card-type %s \n", card.getSlotNumber(), card.getCardType());
				
			}
		} catch ( Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
