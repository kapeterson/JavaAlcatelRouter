package examples;
import java.util.TreeMap;

import router.alcatel.router.AlcatelObjectType;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRCardObject;

public class RouterTest01 {

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		SRChassisObject router = new SRChassisObject();
		router.System.setHostName("LIO1");
		System.out.println("Host name is " + router.System.getHostName());
		
		SRChassisObject chassis = new SRChassisObject();
		chassis.setSerialNumber("3NSFEEE");
		chassis.setPartNumber("3HEPARTNUMBER");
		
		if ( chassis.isChassisObject()){
			System.out.println("Chassis has part number " + chassis.getPartNumber());
		}
		
		SRCardObject card = new SRCardObject(AlcatelObjectType.MDA, 1, "m20-1gb-tx");
		System.out.println("Card " + String.valueOf(card.getSlotNumber()) + " is " + card.getCardType());
		
		chassis.Cards.addCard(card.getSlotNumber(), card);

		card = new SRCardObject(AlcatelObjectType.MDA,2, "m10-1gb-tx");
		chassis.Cards.addCard(card.getSlotNumber(), card);

		card = new SRCardObject(AlcatelObjectType.MDA,3, "m60-1gb-tx");
		chassis.Cards.addCard(card.getSlotNumber(), card);
		
		card = new SRCardObject(AlcatelObjectType.MDA, 10, "imm12-sfp-sfp");
		chassis.Cards.addCard(card.getSlotNumber(), card);
		
		SRCardObject newCard = chassis.Cards.getCard(1);
		System.out.println("New card is " + newCard.getCardType());
		
		System.out.println("Printing all cards\n");
		TreeMap<Integer, SRCardObject> mycards = chassis.Cards.getCards();
		
		for ( Integer slot : mycards.keySet()){
			SRCardObject cardObject = chassis.Cards.getCard(slot);
			System.out.println("\tCARD: " + cardObject.getSlotNumber() + " " + cardObject.getCardType());
		}
		
	}

}
