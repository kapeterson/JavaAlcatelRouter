package parser.alu.config.sr7x50.port;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class PortConfigurationParser extends ConfigurationSection {

	public PortConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.CARD", router, contextChangeHandler);
		System.out.println("Instantiated port configuration parser");

		this.commandHash.put(Pattern.compile("^port ([0-9]+\\/[0-9]+\\/[0-9]+)"), new CommandHandler("setPortContext", true));
	}
	
	public void setPortContext(Matcher matcher){
		System.out.println("Received port " + matcher.group(1));
	}
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
