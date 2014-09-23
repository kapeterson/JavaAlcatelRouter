package parser.alu.config.sr7x50.rsvp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import parser.ConfigurationSection;

public class RSVPConfigurationParser extends ConfigurationSection {
	
	public RSVPConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.RSVP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setRSVPInterfaceContext", true));
	}
	
	
	public void setRSVPInterfaceContext(Matcher matcher){
		
		RSVPInterfaceParser parser = new RSVPInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
