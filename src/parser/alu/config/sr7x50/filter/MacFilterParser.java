package parser.alu.config.sr7x50.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.SRMacFilterObject;

public class MacFilterParser extends ConfigurationSection{
	SRMacFilterObject filter = null;
	
	public MacFilterParser(SRChassisObject router, ContextChange contextChangeHandler, Integer filterNumber){
		super("CONFIG.FILTER.MAC", router, contextChangeHandler);
		
		this.filter = new SRMacFilterObject(filterNumber);
		
		this.commandHash.put(Pattern.compile("^entry ([0-9]+)"), new CommandHandler("addEntry", true));
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));

		

	}
	
	
	public void setDescription(Matcher matcher){
		this.filter.setDescription(matcher.group(1));
	}
	
	public String getDescription(){
		return this.filter.getDescription();
	}
	
	public void addEntry(Matcher matcher){
		//System.out.println("Entry ");
	}
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() <= this.getLastCommandDepth() && this.getLastCommandDepth() <= (this.getSectionDepth() + 3)) {
			//System.out.format("Exited filter %s\n", this.filter.getFilterNumber().toString());
			this.router.Filters.addMacFilter(filter);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
