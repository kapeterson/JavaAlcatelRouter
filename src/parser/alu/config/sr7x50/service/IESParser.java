package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.ConfigurationSection;
import parser.CommandHandler;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRIESObject;
import router.alcatel.router.service.SRServiceInterface;

public class IESParser extends ConfigurationSection{
	
	protected SRIESObject ies = null;
	
	public IESParser(SRChassisObject router, ContextChange contextChangeHandler, Integer vplsnumber){
		super("CONFIG.SERVICE.IES", router, contextChangeHandler);
		ies = new SRIESObject(vplsnumber);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
		this.commandHash.put(Pattern.compile("^interface \"(.*)\" .*"), new CommandHandler("setInterfaceContext", true));
	}
	

	public void setInterfaceContext(Matcher matcher){
		IESInterfaceParser parser = new IESInterfaceParser(this.router, this.getContextNotifier(), matcher.group(1), this.ies);
		parser.setParent(this);
		parser.setSectionDepth(this.getLastCommandDepth());
		this.getContextNotifier().contextChangeCallback(this, parser);
		
	}
	
	public void setDescription(Matcher matcher){
		this.ies.setDescription(matcher.group(1));
	}
	
	public void addInterface(SRServiceInterface iface){
		iface.setParentService(this.ies);
		this.ies.addInterface(iface);
		this.router.Router.Interfaces.addInterface(iface);
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
