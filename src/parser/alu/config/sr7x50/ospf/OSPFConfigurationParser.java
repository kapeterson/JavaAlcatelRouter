package parser.alu.config.sr7x50.ospf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.*;

public class OSPFConfigurationParser extends ConfigurationSection {
	
	public OSPFConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.OSPF", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^area (.*)"), new CommandHandler("setAreaContext", true));
	}
	
	public void setAreaContext(Matcher matcher){
		//System.out.println("Area " + matcher.group(1));
		//System.out.println("Going to ospf iwth last depth of " + this.getLastCommandDepth());
		
		OSPFAreaParser parser = new OSPFAreaParser(router, this.getContextNotifier(), matcher.group(1));
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
