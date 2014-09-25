package parser.alu.config.sr7x50.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parser.CommandHandler;
import parser.ConfigurationSection;
import parser.ContextChange;
import router.alcatel.router.SRChassisObject;
import router.alcatel.router.service.SRServiceInterface;

public class IESInterfaceParser extends ConfigurationSection {
	
	protected SRServiceInterface interfaceObject = null;
	
	public IESInterfaceParser(SRChassisObject router, ContextChange contextChangeHandler, String interfaceName){
		super("CONFIG.SERVICE.IES.INTERFACE", router, contextChangeHandler);
		interfaceObject = new SRServiceInterface(interfaceName);
		this.commandHash.put(Pattern.compile("^description \"(.*)\""), new CommandHandler("setDescription", true));
	}
	

	public void setDescription(Matcher matcher){
		this.interfaceObject.setDescription(matcher.group(1));
	}
	/**
	 * Custom handler
	 */
	public void exitSection(Matcher matcher){
		
		if ( this.getSectionDepth() == this.getLastCommandDepth()) {
			//this.router.Services.addIES(this.interfaceObject);
			((IESParser)this.parent).addInterface(this.interfaceObject);
			this.getContextNotifier().contextChangeCallback(this, this.getParent());
		}
	}
}
