package parser.alu.config.sr7x50.card;

import parser.ConfigurationSection;
import parser.ContextChange;

import parser.alu.config.sr7x50.card.IOMParser;
import router.alcatel.router.SRChassisObject;

import parser.CommandHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardParser extends ConfigurationSection{

	public CardParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^card ([0-9]+)"), new CommandHandler("setCardContext",false));
	}
	
	public void setCardContext(Matcher matcher){
		//System.out.println("Matched card " + matcher.group(1));
		//this.setSectionDepth(this.lastDepth);
		int cnum = Integer.parseInt(matcher.group(1));
		ConfigurationSection iomSection = new IOMParser(this.router, this.contextChange, cnum);
		iomSection.setParent(this);
		iomSection.setSectionDepth(this.lastDepth);
		this.getContextNotifier().contextChangeCallback(this, iomSection);
	}
	
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
