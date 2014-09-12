package parser.alu.config.sr7x50.card;

import parser.ConfigurationSection;
import parser.ContextChange;

import parser.alu.config.sr7x50.card.IOMParser;
import router.alcatel.router.SRChassisObject;

import parser.CommandHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * CardParser handles the main functionality for parsing cards int eh card configuration section <br>
 * When done, the router will have all its card populated
 * 
 * @author Kris Peterson
 *
 */
public class CardParser extends ConfigurationSection{

	/**
	 * Constructor for Card Context parser, 
	 * @param router	SRChassisObject that will be populated
	 * @param contextChangeHandler	ContextChange handler to callback when when chanting context
	 */
	public CardParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^card ([0-9]+)"), new CommandHandler("setCardContext",false));
	}
	

	/**
	 * Change to card context
	 * @param matcher Matcher that contains the card number in the match
	 */
	public void setCardContext(Matcher matcher){

		int cnum = Integer.parseInt(matcher.group(1));
		ConfigurationSection iomSection = new IOMParser(this.router, this.contextChange, cnum);
		iomSection.setParent(this);
		iomSection.setSectionDepth(this.lastDepth);
		this.getContextNotifier().contextChangeCallback(this, iomSection);
		
	}
	
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
