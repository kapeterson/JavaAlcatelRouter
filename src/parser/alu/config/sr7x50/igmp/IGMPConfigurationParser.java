package parser.alu.config.sr7x50.igmp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import parser.alu.config.sr7x50.pim.PIMInterfaceParser;
import router.alcatel.router.*;




public class IGMPConfigurationParser extends ConfigurationSection {
	
	public IGMPConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.IGMP", router, contextChangeHandler);
		//System.out.println("Instantiated port configuration parser");
		this.commandHash.put(Pattern.compile("^interface \"(.*)\""), new CommandHandler("setInterfaceContext", true));
	}
	
	
	public void setInterfaceContext(Matcher matcher){
		//ystem.out.println("PIM Interface " + matcher.group(1));
		
		IGMPInterfaceParser parser = new IGMPInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1));
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
