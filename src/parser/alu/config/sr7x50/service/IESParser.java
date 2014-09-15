package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import parser.ConfigurationSection;

import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRIESObject;

public class IESParser extends ConfigurationSection{
	protected SRIESObject ies = null;
	public IESParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.IES", router, contextChangeHandler);
		ies = new SRIESObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	

	public void setDescription(Matcher matcher){
		this.ies.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			this.router.Services.addIES(this.ies);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
