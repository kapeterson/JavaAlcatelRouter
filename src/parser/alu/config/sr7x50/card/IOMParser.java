package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ContextChange;
import parser.ConfigurationSection;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.*;

/**
 * IOM Parser for parsing all configuration inside each individual card context
 * @author pete
 *
 */
public class IOMParser extends ConfigurationSection {
	
	/** Model of the SRCardObject **/
	SRCardObject card = null;
	
	/**
	 * Instantiates the IOM Parser for a single card
	 * @param router	SRChassisObject to be populated
	 * @param contextChangeHandler	handler to be notified on context change
	 * @param cardNumber	Card number to populate
	 */
	public IOMParser(SRChassisObject router, ContextChange contextChangeHandler, int cardNumber){
		
		super("CONFIG.CARD.IOM", router, contextChangeHandler);
		card = new SRCardObject(cardNumber,"");
		this.commandHash.put(Pattern.compile("^card\\-type (.*)"), new CommandHandler("setCardType",false));
	}
	
	/**
	 * Sets the card type
	 * @param matcher	Matcher containing the card type in group 1
	 */
	public void setCardType(Matcher matcher){
		//System.out.println("Card-type = " + matcher.group(1));
		this.card.setCardType(matcher.group(1));
	}
	
	/**
	 * Custom exitSection handler that adds the parsed card to the router object.
	 * @param	matcher	Matcher that contains exit but is not needed
	 */
	public void exitSection(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {
			//System.out.format("Exiting card %s of card type %s\n", card.getSlotNumber(), card.getCardType() );
			router.Cards.addCard(card.getSlotNumber(), card);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
