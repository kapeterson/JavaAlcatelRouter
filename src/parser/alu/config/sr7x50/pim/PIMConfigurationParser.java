package parser.alu.config.sr7x50.pim;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.*;


public class PIMConfigurationParser extends ConfigurationSection {

	
	public PIMConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.PIM", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
	}
	
	
	public void setInterfaceContext(Matcher matcher){
		//ystem.out.println("PIM Interface " + matcher.group(1));
		
		PIMInterfaceParser parser = new PIMInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
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
