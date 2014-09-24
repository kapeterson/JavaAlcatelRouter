package parser.alu.config.sr7x50.bgp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;


public class BGPConfigurationParser extends ConfigurationSection {

	
	public BGPConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.BGP", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^group \"(.*)\""), new CommandHandler("setGroupContext", true));


	}
	
	
	public void setGroupContext(Matcher matcher){
		
		BGPGroupParser parser = new BGPGroupParser(this.router, this.getContextNotifier(), matcher.group(1));
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
