package parser.alu.config.sr7x50.filter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.AlcatelObject;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.filter.*;

public class IPFilterParser extends ConfigurationSection {

	SRIPFilterObject filter = null;
	
	public IPFilterParser(SRChassisObject router, ContextChange contextChangeHandler, Integer filterNumber){
		super("CONFIG.FILTER.IP", router, contextChangeHandler);
		
		this.filter = new SRIPFilterObject(filterNumber);
		
		this.commandHash.put(Pattern.compile("^entry ([0-9]+) create"), new CommandHandler("setEntryContext", true));
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^default\\-action (forward|drop)$"), new CommandHandler("setDefaultAction", true));
		
		

	}
	
	
	public void setDefaultAction(Matcher matcher){
		String action = matcher.group(1);
		
		if ( action.trim().equals("forward")){
			this.filter.setDefaultAction(FilterAction.Forward);
		} else {
			this.filter.setDefaultAction(FilterAction.Drop);
		}
	}
	
	public void setEntryContext(Matcher matcher){
		//System.out.println("OK");
		IPEntryParser parser = new IPEntryParser(this.router, this.getContextNotifier(), Integer.parseInt(matcher.group(1)));
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
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
	
	public void addObject(AlcatelObject obj){
		
		if ( obj.isIPFilterEntry()){
			this.filter.addEntry((SRIPFilterEntry)obj);
		} else {
			System.out.println("Error: could not add entry to ip filter");
			System.exit(1);
		}
			
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() <= this.getLastCommandDepth() && this.getLastCommandDepth() <= (this.getSectionDepth() + 3)) {
			//System.out.format("Exited filter %s\n", this.filter.getFilterNumber().toString());
			this.router.Filters.addIPFilter(filter);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
