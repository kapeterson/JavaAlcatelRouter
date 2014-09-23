package parser.alu.config.sr7x50.mpls;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class MPLSConfigurationParser extends ConfigurationSection{
	
	public MPLSConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.MPLS", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setMPLSInterfaceContext", true));
	}
	
	
	public void setMPLSInterfaceContext(Matcher matcher){
		//System.out.println("MPLS Interface " + matcher.group(1));
		
		MPLSInterfaceParser parser = new MPLSInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
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
