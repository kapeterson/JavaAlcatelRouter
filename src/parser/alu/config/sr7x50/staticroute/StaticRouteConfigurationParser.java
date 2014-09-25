package parser.alu.config.sr7x50.staticroute;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import parser.alu.config.sr7x50.pim.PIMInterfaceParser;
import router.alcatel.router.SRChassisObject;

public class StaticRouteConfigurationParser extends ConfigurationSection  {
	
	
	
	public StaticRouteConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.STATICROUTE", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^static\\-route (.*)"), new CommandHandler("addStaticRoute", true));
	}
	
	
	public void addStaticRoute(Matcher matcher){

		System.out.println("Found static route " + matcher.group(1));
	}
	
	/**
	 * Use default handler for exiting section
	 */
	public void exitSection(Matcher matcher){
		
		super.defaultExitHandler(matcher);
	}
}
