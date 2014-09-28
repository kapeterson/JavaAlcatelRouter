package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRSAPIngress;

public class SAPIngressParser extends ConfigurationSection {
	
	protected SRSAPIngress sapingress = new SRSAPIngress();
	
	public SAPIngressParser(SRChassisObject router, ContextChange contextChangeHandler)  {
		super("CONFIG.SERVICE.SAP.INGRESS", router, contextChangeHandler);
		this.commandHash.put(Pattern.compile("^qos ([0-9]+)$"), new CommandHandler("setQOSPolicy", true));
		this.commandHash.put(Pattern.compile("^filter ip ([0-9]+)$"), new CommandHandler("setIPFilter", true));

	}
	
	public void setIPFilter(Matcher matcher){
		this.sapingress.setFilter(Integer.parseInt(matcher.group(1)));
	}
	
	
	public void setQOSPolicy(Matcher matcher){
		this.sapingress.setQOS(Integer.parseInt(matcher.group(1)));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.getParent().addObject(this.sapingress);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
