package examples;

import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.SRCardObject;
import router.alcatel.router.card.SRIOMObject;
import router.alcatel.router.card.SRMDAObject;

public class CardPopulateExample01 {

	public static void main(String[] args) {
		
		
		// Create the rouer and set hostname
		SRChassisObject myRouter = new SRChassisObject();
		myRouter.System.setHostName("RCSNTXLVH1005");
		
		
		
		// Create an instance of an IOM for card 1 & set the card type
		
		SRIOMObject card1 = new SRIOMObject(1);
		card1.setCardType("test-card-type");
		
		
		// create and populate an mda
		SRMDAObject mda = new SRMDAObject(1, card1);
		mda.setComplex(1);
		mda.setMDAType("m10-1gb-tx");
		
		
		SRMDAObject mda2 = new SRMDAObject(2, card1);
		mda2.setComplex(2);
		mda2.setMDAType("m12-10gb-tx");
		
		// add the mda to the card in complex 1
		card1.addMDA(1, mda);
		card1.addMDA(2, mda2);
		
		
		// Add the IOM
		myRouter.Cards.addCard(1, card1);
		
		
		// Print all the cards
		for ( Integer slot : myRouter.Cards.getCards().keySet()){
			
			// get the card using the slot number
			SRCardObject thisCard = myRouter.Cards.getCard(slot);
			System.out.println("Card " + slot + " is " + thisCard.getCardType());
			
			
			// verify it's an IOM and not a CPM
			if ( thisCard.isIOMObject()){
				
				// cast the card as an IOM
				SRIOMObject iom = (SRIOMObject)thisCard;
				
				
				// Print the mda in slot 1 & 2
				for ( int i = 1; i <=2; i++){
					
					// get mda i
					SRMDAObject tmda = iom.getMDA(i);
					
					// if it's null print empty, otherwise print the mda type
					if ( tmda != null){
						System.out.println("\tMDA " + i + " " + tmda.getMDAType());
					} else {
						System.out.println("\tMDA " + i + " EMPTY");
					}
						
				}
			}
		}
		
		System.out.println("\n\n\n");
	}

}
