package parser.alu.config.sr7x50.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRIPFilterEntry;

public class IPEntryParser extends ConfigurationSection {

	protected SRIPFilterEntry entry = null;
	
	public IPEntryParser(SRChassisObject router, ContextChange contextChangeHandler, Integer entryNumber){
		super("CONFIG.FILTER.IP.ENTRY", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));

		this.entry = new SRIPFilterEntry(entryNumber);
	}
	
	public void setDescription(Matcher matcher){
		this.entry.setDescription(matcher.group(1));
	}

	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		Integer lastdpth = this.getSectionDepth();
		
		if ( this.getSectionDepth() <= this.getLastCommandDepth() && this.getLastCommandDepth() <= (this.getSectionDepth() + 3)) {

		
			this.getParent().addObject(this.entry);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
