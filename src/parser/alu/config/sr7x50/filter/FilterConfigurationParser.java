package parser.alu.config.sr7x50.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;

public class FilterConfigurationParser extends ConfigurationSection {
	
	public FilterConfigurationParser(SRChassisObject router, ContextChange contextChangeHandler){
		super("CONFIG.FILTER", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^ip\\-filter ([0-9]+) create"), new CommandHandler("setIPFilterContext", true));

	}
	
	
	public void setIPFilterContext(Matcher matcher){
		
		System.out.println("IP filter " + matcher.group(1));
		Integer filternumber = Integer.parseInt(matcher.group(1));
		IPFilterParser parser = new IPFilterParser(this.router, this.getContextNotifier(), filternumber);
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
