package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPEgress;

public class SAPEgressParser extends ConfigurationSection {
	protected SRSAPEgress sapegress = new SRSAPEgress();
	
	public SAPEgressParser(SRChassisObject router, ContextChange contextChangeHandler)  {
		super("CONFIG.SERVICE.SAP.EGRESS", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^qos ([0-9]+)$"), new CommandHandler("setQOSPolicy", true));
		this.commandHash.put(Pattern.compile("^filter ip ([0-9]+)$"), new CommandHandler("setIPFilter", true));
	}
	
	
	public void setIPFilter(Matcher matcher){
		this.sapegress.setFilter(Integer.parseInt(matcher.group(1)));
	}
	
	
	public void setQOSPolicy(Matcher matcher){
		this.sapegress.setQOS(Integer.parseInt(matcher.group(1)));
	}
	
	
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.sapegress);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
