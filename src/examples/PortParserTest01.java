package examples;



import parser.Alcatel7x50ParserManager;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRCardObject;


public class PortParserTest01 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("OK");
		String cfile = "/home/pete/temp/RCSNTXHV0BW010103021LVH01.cfg";
		System.out.println("Going to parse " + cfile);
		

		Alcatel7x50ParserManager pman = new Alcatel7x50ParserManager();

		try {
			pman.ParseConfig(cfile);
			SRChassisObject router = pman.getRouter();
			for ( Integer key : router.Cards.getCards().keySet()){
				
				SRCardObject card = router.Cards.getCard(key);
				System.out.format("\nCard %s  card-type %s \n", card.getSlotNumber(), card.getCardType());
	
				
			}
			
		} catch ( Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
