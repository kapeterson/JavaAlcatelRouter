package parser.alu.config.sr7x50.card;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ContextChange;
import parser.ConfigurationSection;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.card.*;

public class IOMParser extends ConfigurationSection {
	
	SRCardObject card = null;
	public IOMParser(SRChassisObject router, ContextChange contextChangeHandler, int cardNumber){
		
		super("CONFIG.CARD.IOM", router, contextChangeHandler);
		card = new SRCardObject(cardNumber,"");
		this.commandHash.put(Pattern.compile("^card\\-type (.*)"), new CommandHandler("setCardType",false));
	}
	
	public void setCardType(Matcher matcher){
		//System.out.println("Card-type = " + matcher.group(1));
		this.card.setCardType(matcher.group(1));
	}
	
	public void exitSection(Matcher matcher){
		if ( this.getLastCommandDepth() == this.sectionDepth) {
			//System.out.format("Exiting card %s of card type %s\n", card.getSlotNumber(), card.getCardType() );
			router.Cards.addCard(card.getSlotNumber(), card);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
	
}
